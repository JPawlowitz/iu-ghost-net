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
    private String hemisphereLong = "Norden";
    private String hemisphereLat = "Westen";
    private boolean decimalFormat;
    private double decimalLong;
    private double decimalLat;

    public GhostNet getGhostNet() {
        return ghostNet;
    }

    public void setGhostNet(GhostNet ghostNet) {
        this.ghostNet = ghostNet;
    }

    public String getHemisphereLong() {
        return hemisphereLong;
    }

    public void setHemisphereLong(String hemisphereLong) {
        this.hemisphereLong = hemisphereLong;
    }

    public String getHemisphereLat() {
        return hemisphereLat;
    }

    public void setHemisphereLat(String hemisphereLat) {
        this.hemisphereLat = hemisphereLat;
    }

    public boolean isDecimalFormat() {
        return decimalFormat;
    }

    public void setDecimalFormat(boolean decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public double getDecimalLong() {
        return decimalLong;
    }

    public void setDecimalLong(double decimalLong) {
        this.decimalLong = decimalLong;
    }

    public double getDecimalLat() {
        return decimalLat;
    }

    public void setDecimalLat(double decimalLat) {
        this.decimalLat = decimalLat;
    }

    private Hemisphere getHemisphereFromInput(String input) throws Exception {
        switch(input) {
            case "Norden": return Hemisphere.Norden;
            case "Süden": return Hemisphere.Süden;
            case "Westen": return Hemisphere.Westen;
            case "Osten": return Hemisphere.Osten;
            default: throw new Exception("Invalid format");
        }
    }

    public String save() throws Exception {
        //Umrechnung in DMS-Format falls gewählt
        if (this.decimalFormat) {
            this.ghostNet.setLongitude(GpsCoordinate.fromDecimal(this.decimalLong));
            this.ghostNet.setLatitude(GpsCoordinate.fromDecimal(this.decimalLat));
        }

        //Update der Hemisphären von Comboboxen
        GpsCoordinate longitude = this.ghostNet.getLongitude();
        longitude.setHemisphere(getHemisphereFromInput(this.hemisphereLong));
        this.ghostNet.setLongitude(longitude);

        GpsCoordinate latitude = this.ghostNet.getLatitude();
        latitude.setHemisphere(getHemisphereFromInput(this.hemisphereLat));
        this.ghostNet.setLatitude(latitude);

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
