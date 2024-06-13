import jakarta.persistence.*;

@Entity
public class GpsCoordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Hemisphere hemisphere;
    private double degrees;
    private double minutes;
    private double seconds;

    public GpsCoordinate() {
        this.hemisphere = Hemisphere.Norden;
        this.degrees = 0.0;
        this.minutes = 0.0;
        this.seconds = 0.0;
    }

    public Hemisphere getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(Hemisphere hemisphere) {
        this.hemisphere = hemisphere;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public static GpsCoordinate fromDecimal(double decimal) {
        GpsCoordinate coordinate = new GpsCoordinate();
        //Grad
        String decimalString = Double.toString(decimal);
        String truncString = decimalString.split("[.]")[0];
        coordinate.setDegrees(Double.parseDouble(truncString));

        double abs = Math.abs(decimal - coordinate.getDegrees());
        //Minuten
        String absString = Double.toString(60.0 * abs);
        String absTruncString = absString.split("[.]")[0];
        coordinate.setMinutes(Double.parseDouble(absTruncString));
        //Sekunden
        coordinate.setSeconds(3600 * abs - 60 * coordinate.getMinutes());

        return coordinate;
    }

    @Override
    public String toString() {
        String h = "";
        switch (hemisphere) {
            case Norden:
                h = "N";
                break;
            case Süden:
                h = "S";
                break;
            case Westen:
                h = "W";
                break;
            case Osten:
                h = "E";
                break;
        }

        return degrees + "°" + minutes + "'" + seconds + "\"" + h;
    }
}
