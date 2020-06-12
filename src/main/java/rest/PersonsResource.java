package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import errorhandling.NotFoundException;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

@Path("persons")
public class PersonsResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        return GSON.toJson(FACADE.getAllPersons());
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") Long id) throws NotFoundException{
        return GSON.toJson(FACADE.getPersonById(id));
    }


    @GET
    @Path("/phone/{phoneNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByPhoneNo(@PathParam("phoneNo") String phoneNo) throws NotFoundException{
        return GSON.toJson(FACADE.getPersonByPhone(phoneNo));
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsByHobby(@PathParam("email") String email) throws NotFoundException{
        return GSON.toJson(FACADE.getPersonByEmail(email));
    }

}
