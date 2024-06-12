import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PersonController implements Serializable {
    @Inject
    private AppController appController;
    private String name = "";
    private String phoneNumber = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String report() {
        Reporter reporter = new Reporter();
        reporter.setName(this.name);
        reporter.setPhoneNumber(this.phoneNumber);
        appController.setUser(reporter);

        return "overview";
    }

    public String salvage() {
        Salvager salvager = new Salvager();
        salvager.setName(this.name);
        salvager.setPhoneNumber(this.phoneNumber);
        appController.setUser(salvager);

        return "overview";
    }
}
