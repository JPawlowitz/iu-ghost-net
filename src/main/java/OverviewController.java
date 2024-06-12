import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class OverviewController implements Serializable {
    @Inject
    private AppController appController;
    private GhostNetDAO ghostNetDAO;
    private List<GhostNet> ghostNets = new ArrayList<>();

    public OverviewController() {
        ghostNetDAO = new GhostNetDAO();
        ghostNets = ghostNetDAO.findAll();
    }

    public List<GhostNet> getGhostNets() {
        return ghostNets;
    }

    public void setGhostNets(List<GhostNet> ghostNets) {
        this.ghostNets = ghostNets;
    }

    public void setNetStatusRecoveryCancelled(GhostNet ghostNet) {
        ghostNet.setStatus(NetStatus.Gemeldet);
        ghostNet.setSalvager(null);
        ghostNetDAO.merge(ghostNet);
    }

    public void setNetStatusRecovery(GhostNet ghostNet) {
        ghostNet.setStatus(NetStatus.Bergung);
        ghostNet.setSalvager((Salvager)appController.getUser());
        ghostNetDAO.merge(ghostNet);
    }

    public void setNetStatusRecovered(GhostNet ghostNet) {
        ghostNet.setStatus(NetStatus.Geborgen);
        ghostNetDAO.merge(ghostNet);
    }

    public void setNetStatusLost(GhostNet ghostNet) {
        ghostNet.setStatus(NetStatus.Verschollen);
        ghostNetDAO.merge(ghostNet);
    }

    public String logout() {
        appController.setUser(null);
        return "index";
    }
}
