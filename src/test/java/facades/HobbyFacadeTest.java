package facades;

import dto.HobbyDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

public class HobbyFacadeTest {

    private static EntityManagerFactory emf;
    private static HobbyFacade facade;
    private static Person p1, p2, p3;
    private static Hobby hobby1, hobby2, hobby3, hobby4;
    private static Address address1, address2, address3;

    public HobbyFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,
                Strategy.DROP_AND_CREATE);
        facade = HobbyFacade.getHobbyFacade(emf);
    }

//    @AfterAll
//    public static void tearDownClass() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        address1 = new Address("KagsåKollegiet", "Copenhagen", "2300");
        address2 = new Address("Fredensbovej", "Hvidovre", "2650");
        address3 = new Address("Kattevej", "Ballerup", "2750");

        hobby1 = new Hobby("Gaming", "Wasting time in front of computer or TV");
        hobby2 = new Hobby("Swimming", "Getting wet");
        hobby3 = new Hobby("Fishing", "Getting up early and doing nothing for 5 hours");
        hobby4 = new Hobby("D&D", "Very nerdy game");

        List<Hobby> hobbiesL1 = new ArrayList<>();
        List<Hobby> hobbiesL2 = new ArrayList<>();
        List<Hobby> hobbiesL3 = new ArrayList<>();
        hobbiesL1.add(hobby1);
        hobbiesL1.add(hobby2);
        hobbiesL2.add(hobby1);
        hobbiesL2.add(hobby3);
        hobbiesL3.add(hobby4);
        hobbiesL3.add(hobby2);

        p1 = new Person("Caroline", "HoegIversen", "carol@hoeg.iversen", "20394857", hobbiesL1, address1);
        p2 = new Person("Tobias", "AnkerB-J", "tobias@anker.boldtJ", "12345678", hobbiesL2, address2);
        p3 = new Person("Allan", "Simonsen", "allan@bo.simonsen", "98786756", hobbiesL3, address3);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();

            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(hobby3);
            em.persist(hobby4);

            em.persist(address1);
            em.persist(address2);
            em.persist(address3);

            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

       @Test
    public void testGetHobbyCount() {
        System.out.println("getHobbyCount");
        long hobbies = facade.getHobbyCount();
        assertEquals(4.0, hobbies);
    }
    
    @Test
    public void testGetAllHobbies() {
        System.out.println("getAllHobbies");
        List<HobbyDTO> hobbies = facade.getAllHobbies();
        assertEquals(4, hobbies.size());
    }

}
