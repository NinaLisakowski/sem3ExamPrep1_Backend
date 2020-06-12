package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import errorhandling.NotFoundException;
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

public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person p1, p2, p3;
    private static Hobby hobby1, hobby2, hobby3, hobby4;
    private static Address address1, address2, address3;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,
                Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getPersonFacade(emf);
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
    public void testGetAllPersons() {
        System.out.println("getAllPersons");
        List<PersonDTO> persons = facade.getAllPersons();
        assertEquals(3, persons.size());
    }

    @Test
    public void testgetPersonById() throws NotFoundException {
        System.out.println("getPersonByID");
        Long expectedID = p1.getId();
        PersonDTO result = facade.getPersonById(expectedID);
        assertEquals(result.getId(), expectedID);
    }
    
    @Test
    public void testGetPersonByEmail() throws NotFoundException {
        System.out.println("getAllPersonByEmail");
        String expectedEmail = p2.getEmail();
        PersonDTO resultPerson = facade.getPersonByEmail(expectedEmail);
        assertEquals(resultPerson.getEmail(), expectedEmail);
    }

    @Test
    public void testGetPersonByPhone() throws NotFoundException {
        System.out.println("getAllPersonByPhone");
        String expectedPhone = p3.getPhone();
        PersonDTO resultPerson = facade.getPersonByPhone(expectedPhone);
        assertEquals(resultPerson.getPhone(), expectedPhone);
    }
    
//    //assertfailure with exception
//    @Test
//    public void testgetPersonByIdFail() {
//        System.out.println("getPersonByIDFail");
//        Assertions.assertThrows(NotFoundException.class, () -> {
//            facade.getPersonById(0);
//        });
//    }
//
//    @Test
//    public void testAddPerson() throws WrongPersonFormatException, IllegalArgumentException, IllegalAccessException {
//        //Person Data
//        String fName = "Jane";
//        String lName = "Doe";
//        String eMail = "jane@doe.com";
//        String city = "Copenhagen";
//        String zip = "1700";
//        String street = "West Street";
//        String hobbyNames = "programming, dancing";
//        String phoneNumber = "479865";
//        //Make Person
//        Person p = new Person(eMail, fName, lName);
//        //Make Address
//        CityInfo cityInfo = new CityInfo(city, zip);
//        Address adr = new Address(street, "Additional info", cityInfo);
//        p.setAddress(adr);
//        //Make Hobbies
//        List<Hobby> hobbiesList = new ArrayList();
//        hobbiesList.add(new Hobby("programming", "abc"));
//        hobbiesList.add(new Hobby("dancing", "def"));
//        p.setHobbies(hobbiesList);
//        //Make Phone
//        Set<Phone> phoneNumbers = new HashSet();
//        phoneNumbers.add(new Phone(phoneNumber, "Phone Description"));
//        p.setPhones(phoneNumbers);
//        PersonDTO expectedPersonResult = new PersonDTO(p);
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setfName(fName);
//        cpDTO.setlName(lName);
//        cpDTO.setEmail(eMail);
//        cpDTO.setStreet(street);
//        cpDTO.setCity(city);
//        cpDTO.setZip(zip);
//        cpDTO.setHobbyName(hobbyNames);
//        cpDTO.setHobbyDescription("abc, def");
//        cpDTO.setPhoneNumber(phoneNumber);
//        cpDTO.setPhoneDescription("Phone description");
//        cpDTO.setadditionalAddressInfo("additional Address info");
//        PersonDTO actualAddPersonResult = facade.addPerson(cpDTO);
//        //Test are not run syncronized, therfore we force the id to be the same.
//        expectedPersonResult.setId(actualAddPersonResult.getId());
//        assertTrue(expectedPersonResult.equals(actualAddPersonResult));
//    }
//
//    @Test
//    public void testAddPersonMissingInput() {
//        System.out.println("AddPersonMissingInput");
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setfName("FirstName");
//        cpDTO.setlName("LastName");
//        Assertions.assertThrows(WrongPersonFormatException.class, () -> {
//            facade.addPerson(cpDTO);
//        });
//    }
//
//    @Test
//    public void testAddPersonWrongHobbyInput() {
//        System.out.println("testAddPersonWrongHobbyInput");
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setfName("FirstName");
//        cpDTO.setlName("LastName");
//        cpDTO.setCity("city");
//        cpDTO.setEmail("mail");
//        cpDTO.setHobbyName("hby1, hby2");
//        cpDTO.setHobbyDescription("desc1");
//        cpDTO.setPhoneNumber("752");
//        cpDTO.setPhoneDescription("description");
//        cpDTO.setStreet("street");
//        cpDTO.setZip("zip");
//        cpDTO.setadditionalAddressInfo("additional info");
//        Assertions.assertThrows(WrongPersonFormatException.class, () -> {
//            facade.addPerson(cpDTO);
//        });
//    }
//
//    @Test
//    public void testAddPersonExistingPhone() {
//        System.out.println("testAddPersonExistingPhone");
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setfName("FirstName");
//        cpDTO.setlName("LastName");
//        cpDTO.setCity("city");
//        cpDTO.setEmail("mail");
//        cpDTO.setHobbyName("hby1, hby2");
//        cpDTO.setHobbyDescription("desc1, desc2");
//        cpDTO.setPhoneNumber("29384756");//Allready exists in setup
//        cpDTO.setPhoneDescription("description");
//        cpDTO.setStreet("street");
//        cpDTO.setZip("zip");
//        cpDTO.setadditionalAddressInfo("additional info");
//        Assertions.assertThrows(WrongPersonFormatException.class, () -> {
//            facade.addPerson(cpDTO);
//        });
//    }
//
//    @Test
//    public void testEditPerson() throws WrongPersonFormatException, NotFoundException, IllegalArgumentException, IllegalAccessException {
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setId(p1.getId());
//        cpDTO.setfName("FirstNameEdit");
//        cpDTO.setlName("LastNameEdit");
//        cpDTO.setCity("cityEdit");
//        cpDTO.setEmail("mailEdit");
//        cpDTO.setHobbyName("hby1Edit, hby2Edit");
//        cpDTO.setHobbyDescription("desc1Edit, desc2Edit");
//        cpDTO.setPhoneNumber("29384756Edit");
//        cpDTO.setPhoneDescription("descriptionEdit");
//        cpDTO.setStreet("streetEdit");
//        cpDTO.setZip("zipEdit");
//        cpDTO.setadditionalAddressInfo("additional infoEdit");
//        PersonDTO editPerson = facade.editPerson(cpDTO);
//        assertEquals(cpDTO.getfName(), editPerson.getfName());
//        assertEquals(cpDTO.getZip(), editPerson.getZip());
//    }
//    
//    @Test
//    public void testEditPersonWithoutFirstName() throws WrongPersonFormatException, NotFoundException, IllegalArgumentException, IllegalAccessException{
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setId(p1.getId());
//        cpDTO.setlName("LastNameEdit");
//        cpDTO.setCity("cityEdit");
//        cpDTO.setEmail("mailEdit");
//        cpDTO.setHobbyName("hby1Edit, hby2Edit");
//        cpDTO.setHobbyDescription("desc1Edit, desc2Edit");
//        cpDTO.setPhoneNumber("29384756Edit");
//        cpDTO.setPhoneDescription("descriptionEdit");
//        cpDTO.setStreet("streetEdit");
//        cpDTO.setadditionalAddressInfo("additional infoEdit");
//        PersonDTO editPerson = facade.editPerson(cpDTO);
//        assertEquals(p1.getfName(), editPerson.getfName());
//        assertEquals(p1.getAddress().getCityInfo().getZipCode(), editPerson.getZip());
//    }
//    
//    @Test
//    public void testEditPersonAllanGoesFishing_AndStartsReading() throws WrongPersonFormatException, NotFoundException, IllegalArgumentException, IllegalAccessException{
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setId(p3.getId());
//        cpDTO.setPhoneNumber("1234567890");
//        cpDTO.setPhoneDescription("ApePhone");
//        cpDTO.setHobbyName("Fishing, Gaming, Reading");
//        cpDTO.setHobbyDescription(" , , Wasting alot of hours");
//        PersonDTO editPerson = facade.editPerson(cpDTO);
//        assertEquals(cpDTO.getHobbyName(), editPerson.getHobbies());
//        assertEquals(cpDTO.getPhoneNumber(), editPerson.getPhones().iterator().next());
//    }
//    
//    @Test
//    public void testEditPersonWrongID() {
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setId(0L);
//        cpDTO.setlName("LastNameEdit");
//        cpDTO.setCity("cityEdit");
//        cpDTO.setEmail("mailEdit");
//        cpDTO.setHobbyName("hby1Edit, hby2Edit");
//        cpDTO.setHobbyDescription("desc1Edit, desc2Edit");
//        cpDTO.setPhoneNumber("29384756Edit");
//        cpDTO.setPhoneDescription("descriptionEdit");
//        cpDTO.setStreet("streetEdit");
//        cpDTO.setZip("zipEdit");
//        cpDTO.setadditionalAddressInfo("additional infoEdit");
//        Assertions.assertThrows(NoContentFoundException.class, () -> {
//            facade.editPerson(cpDTO);
//        });
//    }
//    
//    @Test
//    public void testEditPersonExistingPhone() {
//        System.out.println("testEditPersonExistingPhone");
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setPhoneNumber("29384756");//Allready exists in setup
//        Assertions.assertThrows(WrongPersonFormatException.class, () -> {
//            facade.addPerson(cpDTO);
//        });
//    }
//
//    @Test
//    public void testEditPersonWrongHobbyInput(){
//        System.out.println("testEditPersonWrongHobbyInput");
//        CompletePersonDTO cpDTO = new CompletePersonDTO();
//        cpDTO.setHobbyName("hby1, hby2");
//        cpDTO.setHobbyDescription("desc1");
//        Assertions.assertThrows(WrongPersonFormatException.class, () -> {
//            facade.addPerson(cpDTO);
//        });
//    }
//    
//    @Test
//    public void testGetPersonByPhone() throws NotFoundException {
//        System.out.println("GetPersonByPhone");
//        PersonDTO person = facade.getPersonByPhone(p1.getPhoneNumbers().iterator().next());
//        assertEquals(new PersonDTO(p1), person);
//    }
//
//    //expect error
//    @Test
//    public void testGetPersonByPhoneFail() {
//        System.out.println("GetPersonByPhoneFail");
//        Assertions.assertThrows(NotFoundException.class, () -> {
//            facade.getPersonByPhone("00000000");
//        });
//    }
//
//   
//
//    // expect error
//    @Test
//    public void testGetPersonsByHobbyFail() {
//        System.out.println("GetPersonsByHobbyFail");
//        Assertions.assertThrows(NotFoundException.class, () -> {
//            facade.getAllPersonsByHobby("hullabulla");
//        });
//    }
//
//    @Test
//    public void testGetPersonsFromCity() throws NotFoundException {
//        System.out.println("getPersonsFromCity");
//        PersonsDTO persons = facade.getPersonsFromCity(city3.getCity());
//        assertEquals(1, persons.getPersonsList().size());
//    }
//
//    //expect error
//    @Test
//    public void testGetPersonsFromCityFail() {
//        System.out.println("getPersonsFromCityFail");
//        Assertions.assertThrows(NoContentFoundException.class, () -> {
//            facade.getPersonsFromCity("Langbortistan");
//        });
//    }
//
//    @Test
//    public void testGetPersonCountWithHobby() {
//        System.out.println("getAllPersonsByHobby");
//        assertEquals(1, facade.getAmountOfPersonsWithHobby("Swimming"));
//        assertEquals(1, facade.getAmountOfPersonsWithHobby("Fishing"));
//        assertEquals(1, facade.getAmountOfPersonsWithHobby("D&D"));
//        assertEquals(2, facade.getAmountOfPersonsWithHobby("Gaming"));
//    }
//
//    @Test
//    public void testGetPersonCountWithHobbyZero() {
//        System.out.println("getAllPersonsByHobby");
//        assertEquals(0, facade.getAmountOfPersonsWithHobby("HullaBulla"));
//        assertEquals(0, facade.getAmountOfPersonsWithHobby("DuErDenBedste"));
//    }
}
