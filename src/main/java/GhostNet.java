import jakarta.persistence.*;

@Entity
public class GhostNet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private GpsCoordinate longitude;
    @OneToOne(cascade = CascadeType.ALL)
    private GpsCoordinate latitude;
    private int size;
    @Enumerated(EnumType.STRING)
    private NetStatus status;
    @OneToOne(cascade = CascadeType.ALL)
    private Reporter reporter;
    @ManyToOne(cascade = CascadeType.ALL)
    private Salvager salvager;

    public GhostNet() {
        this.longitude = new GpsCoordinate();
        this.latitude = new GpsCoordinate();
        this.size = 1;
        this.status = NetStatus.Gemeldet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GpsCoordinate getLongitude() {
        return longitude;
    }

    public void setLongitude(GpsCoordinate longitude) {
        this.longitude = longitude;
    }

    public GpsCoordinate getLatitude() {
        return latitude;
    }

    public void setLatitude(GpsCoordinate latitude) {
        this.latitude = latitude;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public NetStatus getStatus() {
        return status;
    }

    public void setStatus(NetStatus status) {
        this.status = status;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public Salvager getSalvager() {
        return salvager;
    }

    public void setSalvager(Salvager salvager) {
        this.salvager = salvager;
    }
}
