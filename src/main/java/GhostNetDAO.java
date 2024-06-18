import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class GhostNetDAO {
    private EntityManager entityManager;

    public GhostNetDAO() {
        entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    public List<GhostNet> findAll() {
        Query q = entityManager.createQuery("select g from GhostNet g");
        List<GhostNet> result = q.getResultList();

        return result;
    }

    public void merge(GhostNet ghostNet) {
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        entityManager.merge(ghostNet);
        t.commit();
    }

    public void persist(GhostNet ghostNet) {
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        entityManager.persist(ghostNet);
        t.commit();
    }
}
