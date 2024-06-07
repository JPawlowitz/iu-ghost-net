import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.Query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class NetList {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private List<GhostNet> ghostNets = new ArrayList<>();

    public List<GhostNet> getGhostNets() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT g FROM GhostNet g");
        ghostNets = q.getResultList();

        return ghostNets;
    }
}
