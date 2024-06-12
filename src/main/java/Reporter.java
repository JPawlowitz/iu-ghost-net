import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Reporter extends Person {
    @Column(nullable = true)
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
