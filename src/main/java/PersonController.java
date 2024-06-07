import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class PersonController implements Serializable {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private List<Person> people = new ArrayList<>();

    public PersonController() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("Select p from Person p");
        people = q.getResultList();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
}
