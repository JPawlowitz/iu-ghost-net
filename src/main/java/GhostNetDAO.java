import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class GhostNetDAO {
    EntityManager em;

    public GhostNetDAO() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    public List<GhostNet> findAll() {
        Query q = em.createQuery("select g from GhostNet g");
        List<GhostNet> result = q.getResultList();

        return result;
    }

    public void merge(GhostNet ghostNet) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(ghostNet);
        t.commit();
    }

    public void persist(GhostNet ghostNet) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(ghostNet);
        t.commit();
    }
}
