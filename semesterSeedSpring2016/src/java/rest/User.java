package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Role;
import facades.UserFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.minidev.json.JSONObject;
import security.PasswordStorage;

@Path("demouser")
@RolesAllowed("User")
public class User {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    UserFacade fc = new UserFacade();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public void addUser(String user) throws PasswordStorage.CannotPerformOperationException {
        JsonObject newUser = new JsonParser().parse(user).getAsJsonObject();
        entity.User u = new entity.User();
        u.setUserName(newUser.get("username").getAsString());
        u.setPassword(PasswordStorage.createHash(newUser.get("password").getAsString()));

        fc.addPerson(u);
     //   return gson.toJson(u);
    }
}
