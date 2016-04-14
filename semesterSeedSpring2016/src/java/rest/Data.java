/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import security.PasswordStorage;

/**
 * REST Web Service
 *
 * @author Magnus
 */
@Path("data")
public class Data {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    UserFacade fc = new UserFacade();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Data
     */
    public Data() {
    }

    /**
     * Retrieves representation of an instance of rest.Data
     *
     * @return an instance of java.lang.String
     */
    /**
     * PUT method for updating or creating an instance of Data
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dailyrates")
    public String getRates() {
        return gson.toJson(fc.getRates());
    }

}
