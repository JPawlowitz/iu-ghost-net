import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class ReportController implements Serializable {
    @Inject
    AppController appController;

    private GhostNet ghostNet = new GhostNet();
    private String hemisphereValue = "North";

    public GhostNet getGhostNet() {
        return ghostNet;
    }

    public void setGhostNet(GhostNet ghostNet) {
        this.ghostNet = ghostNet;
    }

    public String getHemisphereValue() {
        return hemisphereValue;
    }

    public void setHemisphereValue(String hemisphereValue) {
        this.hemisphereValue = hemisphereValue;
    }
    public String save() {
        GpsCoordinate location = this.ghostNet.getLocation();
        switch(this.hemisphereValue) {
            case "Norden": location.setHemisphere(Hemisphere.Norden);
                break;
            case "Süden": location.setHemisphere(Hemisphere.Süden);
                break;
            case "Westen": location.setHemisphere(Hemisphere.Westen);
                break;
            case "Osten": location.setHemisphere(Hemisphere.Osten);
                break;
        }
        this.ghostNet.setLocation(location);
        this.ghostNet.setReporter(appController.getReporter());

        appController.addGhostNet(this.ghostNet);

        return "overview";
    }

    public String cancel() {
        if (appController.userIsSalvager()) {
            return "overview";
        }

        appController.setUser(null);
        return "index";
    }
}
