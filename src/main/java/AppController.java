import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@ApplicationScoped
public class AppController {
    private GhostNetDAO ghostNetDAO;
    private Person user = new Person();

    public AppController() {
        ghostNetDAO = new GhostNetDAO();
    }

    public List<GhostNet> getGhostNets() {
        List<GhostNet> list = ghostNetDAO.findAll();
        GhostNet test = new GhostNet();
        test.setStatus(NetStatus.Geborgen);
        list.add(test);
        GhostNet t = new GhostNet();
        t.setStatus(NetStatus.Bergung);
        Salvager s = new Salvager();
        s.setName("Frank");
        t.setSalvager(s);
        list.add(t);

        GhostNet b = new GhostNet();
        b.setStatus(NetStatus.Bergung);
        Salvager se = new Salvager();
        se.setName("Klaus");
        b.setSalvager(se);
        list.add(b);
        return list;
    }

    public void addGhostNet(GhostNet ghostNet) {
        ghostNetDAO.persist(ghostNet);
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public boolean userIsSalvager() {
        return this.user instanceof Salvager;
    }
}
