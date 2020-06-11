package dto;

import entities.Person;
import java.util.List;

/**
 *
 * @author Nina
 */
public class PersonDTO {
    
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
    private List<HobbyDTO> hobbies;

    public PersonDTO() {
    }

    public PersonDTO(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
     public PersonDTO(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.phone = person.getPhone();
        this.hobbies = HobbyDTO.convertHobbyListToDTO(person.getHobbies());
        this.address = new AddressDTO(person.getAddress());
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
}
