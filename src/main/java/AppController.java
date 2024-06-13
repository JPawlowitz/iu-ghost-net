import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class AppController {
    private GhostNetDAO ghostNetDAO;
    private Person user = new Person();

    public AppController() {
        ghostNetDAO = new GhostNetDAO();
    }

    public void addGhostNet(GhostNet ghostNet) {
        ghostNetDAO.merge(ghostNet);
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

    public Reporter getReporter() {
        if (this.user == null) {
            return null;
        }

        if (this.user instanceof Salvager) {
            return ((Salvager) this.user).toReporter();
        }
        return (Reporter)this.user;
    }
}
