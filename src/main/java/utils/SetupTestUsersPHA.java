package utils;

import entities.Address;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nina
 */
public class SetupTestUsersPHA {
     public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
        EntityManager em = emf.createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        // Also, either delete this file, when users are created or rename and add to .gitignore
        // Whatever you do DO NOT COMMIT and PUSH with the real passwords
        Hobby hobby1 = new Hobby("Swimming", "Kinda wet");
        Hobby hobby2 = new Hobby("Football", "Kicking a ball");
        Hobby hobby3 = new Hobby("Tennis", "Small yellow-ish ball");
        Hobby hobby4 = new Hobby("Haandbold", "Running peolpe with a ball");
        Address address1 = new Address("Copenhagenvej", "Copenhagen", "2300");
        Address address2 = new Address("Herningsvej", "Herning", "7429");
        Address address3 = new Address("Glostrupvej", "Glostrup", "2600");
        List<Hobby> hobbies1 = new ArrayList<>();
        List<Hobby> hobbies2 = new ArrayList<>();
        List<Hobby> hobbies3 = new ArrayList<>();

        hobbies1.add(hobby1);
        hobbies1.add(hobby2);
        hobbies2.add(hobby3);
        hobbies2.add(hobby4);
        hobbies3.add(hobby1);
        hobbies3.add(hobby4);

        Person person1 = new Person("Khabib", "Nurmagomedov", "LwChamp@gmail.com", "12345678");
        Person person2 = new Person("Tony", "Ferguson", "PplChamp@gmail.com", "12345678");
        Person person3 = new Person("Mohamed", "Salah", "Pharaoh@gmail.com", "12345678");

        person1.addHobbyToPerson(hobby1);
        person2.addHobbyToPerson(hobby1);
        person3.addHobbyToPerson(hobby2);
        person1.addHobbyToPerson(hobby2);
        person3.addHobbyToPerson(hobby4);

        person1.addAddressToPerson(address2);
        person2.addAddressToPerson(address2);
        person3.addAddressToPerson(address1);

        em.getTransaction().begin();
        em.persist(hobby1);
        em.persist(hobby2);
        em.persist(hobby3);
        em.persist(hobby4);
        em.persist(address1);
        em.persist(address2);
        em.persist(address3);
        em.persist(person1);
        em.persist(person2);
        em.persist(person3);
        em.getTransaction().commit();
    }
}
