import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class ReportController implements Serializable {
    @Inject
    NetList list;

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
            case "North": location.setHemisphere(Hemisphere.North);
                break;
            case "South": location.setHemisphere(Hemisphere.South);
                break;
            case "West": location.setHemisphere(Hemisphere.West);
                break;
            case "East": location.setHemisphere(Hemisphere.East);
                break;
        }
        this.ghostNet.setLocation(location);
        list.addGhostNet(this.ghostNet);

        return "index";
    }

    public String cancel() {
        return "index";
    }
}
