import jakarta.persistence.Entity;

@Entity
public class Salvager extends Person {
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Reporter toReporter() {
        Reporter reporter = new Reporter();
        reporter.setName(this.getName());
        reporter.setPhoneNumber(this.phoneNumber);

        return reporter;
    }
}
