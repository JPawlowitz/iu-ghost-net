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

    @Override
    public String toString() {
        String h = "";
        switch (hemisphere) {
            case North:
                h = "N";
                break;
            case South:
                h = "S";
                break;
            case West:
                h = "W";
                break;
            case East:
                h = "E";
                break;
        }

        return degrees + "°" + minutes + "'" + seconds + "\"" + h;
    }
}
