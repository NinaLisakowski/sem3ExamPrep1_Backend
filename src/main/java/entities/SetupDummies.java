package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class SetupDummies {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/sem3ExamPrep1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.DROP_AND_CREATE);

    public static void main(String[] args) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        addEntities();
    }

    private static void addEntities() {
        EntityManager em = EMF.createEntityManager();
        List<Hobby> hobbiesL1 = new ArrayList<>();
        List<Hobby> hobbiesL2 = new ArrayList<>();
        try {
            em.getTransaction().begin();
            Hobby hobby1 = new Hobby("MMA", "Beating people up");
            Hobby hobby2 = new Hobby("Football", "Most popular sport");
            Hobby hobby3 = new Hobby("Hockey", "Canadians love it");
            Hobby hobby4 = new Hobby("Acting", "Pretending you're cool");
            Hobby hobby5 = new Hobby("Knitting", "Popular with babushkas");

            Address address1 = new Address("Jensensgade", "København", "2400");
            Address address2 = new Address("Jørgensesgade", "København", "2400");
            Address address3 = new Address("Johnsensgade", "København", "2400");
            Address address4 = new Address("Johansensgade", "København", "2400");
            Address address5 = new Address("Jimmysgade", "Randers", "4200");

            hobbiesL1.add(hobby1);
            hobbiesL1.add(hobby2);
            hobbiesL1.add(hobby1);
            hobbiesL2.add(hobby3);
            hobbiesL2.add(hobby4);

            Person person1 = new Person("Khabib", "Nurmagomedov", "LwChamp@gmail.com", "12345678", hobbiesL1, address1);
            Person person2 = new Person("Tony", "Ferguson", "PplChamp@gmail.com", "56473829", hobbiesL2, address2);
            Person person3 = new Person("Mohamed", "Salah", "Pharaoh@gmail.com", "90785634", hobbiesL2, address3);
            Person person4 = new Person("Virgil", "van Dijk", "TopDefender@gmail.com", "10293847", hobbiesL1, address4);
            Person person5 = new Person("Keanu", "Reeves", "RealNeo@gmail.com", "91827364", hobbiesL2, address5);

            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(hobby3);
            em.persist(hobby4);
            em.persist(hobby5);

            em.persist(address1);
            em.persist(address2);
            em.persist(address3);
            em.persist(address4);
            em.persist(address5);

            em.persist(person1);
            em.persist(person2);
            em.persist(person3);
            em.persist(person4);
            em.persist(person5);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
