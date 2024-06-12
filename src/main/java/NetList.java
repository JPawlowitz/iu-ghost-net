import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@ApplicationScoped
public class NetList {
    private GhostNetDAO ghostNetDAO;

    public NetList() {
        ghostNetDAO = new GhostNetDAO();
    }

    public List<GhostNet> getGhostNets() {
        return ghostNetDAO.findAll();
    }

    public void addGhostNet(GhostNet ghostNet) {
        ghostNetDAO.persist(ghostNet);
    }
}
