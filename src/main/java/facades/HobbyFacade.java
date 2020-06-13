package facades;

import dto.HobbyDTO;
import entities.Hobby;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/*
 * @author Nina
 */
public class HobbyFacade {
    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getHobbyCount() {
        EntityManager em = getEntityManager();
        try {
            long hobbyCount = (long) em.createQuery("SELECT COUNT(h) FROM Hobby h").getSingleResult();
            return hobbyCount;
        } finally {
            em.close();
        }
    }
    
        public List<HobbyDTO> getAllHobbies() {
        EntityManager em = getEntityManager();
        List<HobbyDTO> hDTOList = new ArrayList();
        try {
            TypedQuery<Hobby> q = em.createNamedQuery("Hobby.getAll", Hobby.class);
            for (Hobby h : q.getResultList()) {
                hDTOList.add(new HobbyDTO(h));
            }
            return hDTOList;
        } finally {
            em.close();
        }
    }
        
        
    
}
