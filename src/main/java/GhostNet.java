import jakarta.persistence.*;

@Entity
public class GhostNet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private GpsCoordinate location;
    private int size;
    @Enumerated(EnumType.STRING)
    private NetStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GpsCoordinate getLocation() {
        return location;
    }

    public void setLocation(GpsCoordinate location) {
        this.location = location;
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
}
