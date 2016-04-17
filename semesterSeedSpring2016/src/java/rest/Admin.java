package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    UserFacade fc = new UserFacade();

//  @GET
//  @Produces(MediaType.APPLICATION_JSON)
//  public String getSomething(){
//    String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
//    return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
//            +"\"serverTime\": \""+now +"\"}"; 
//  }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public String getUsers() {
        JsonArray result = new JsonArray();
        List<entity.User> users = fc.getUsers();
        for (entity.User u : users) {
            JsonObject p1 = new JsonObject();
            p1.addProperty("username", u.getUserName());

            JsonArray roles = new JsonArray();
            List<entity.Role> r1 = u.getRoles();
            for (entity.Role r : r1) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("role", r.getRoleName());
                roles.add(p2);
            }
            p1.add("roles", roles);

            result.add(p1);
        }
        return gson.toJson(result);
    }

    @DELETE
    @Path("/user/{username}")
    @Produces("application/json")
    public String deleteUser(@PathParam("username") String username) {
        entity.User c = fc.deleteUser(username);
        
         //return gson.toJson(c);

        JsonObject jo = new JsonObject();
        jo.addProperty("userName", c.getUserName());
        return gson.toJson(jo);
    }

}
