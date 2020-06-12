package facades;

import dto.PersonDTO;
import entities.Person;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/*
 * @author Nina
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
        public List<PersonDTO> getAllPersons() {
        EntityManager em = getEntityManager();
        List<PersonDTO> pDTOList = new ArrayList();
        try {
            TypedQuery<Person> q = em.createNamedQuery("Person.getAll", Person.class);
            for (Person p : q.getResultList()) {
                pDTOList.add(new PersonDTO(p));
            }
            return pDTOList;
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonById(Long id) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p == null) {
                throw new NotFoundException("No person found with this id");
            }
            return new PersonDTO(p);
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonByEmail(String email) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.createQuery("SELECT p FROM Person p WHERE p.email = :email ", Person.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if(p == null) {
                throw new NotFoundException("No person found with this email");
            }
            return new PersonDTO(p);
            
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonByPhone(String phone) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.createQuery("SELECT p FROM Person p WHERE p.phone = :phone", Person.class).setParameter("phone", phone).getSingleResult();
            if (p == null) {
                throw new NotFoundException("No person found with this phone");
            }
            return new PersonDTO(p);
        } finally {
            em.close();
        }
    }


}
