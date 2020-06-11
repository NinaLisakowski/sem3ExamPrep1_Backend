package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Nina
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE FROM Person"),
    @NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p")
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Hobby> hobbies = new ArrayList();
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String phone) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addAddressToPerson(Address address) {
        this.address = address;
        if (!address.getPersons().contains(this)) {
            address.getPersons().add(this);
        }
    }

    public void addHobbyToPerson(Hobby hobby) {
        if (!this.hobbies.contains(hobby)) {
            this.hobbies.add(hobby);
        }
        if (!hobby.getPersons().contains(this)) {
            hobby.getPersons().add(this);
        }
    }

   
}
