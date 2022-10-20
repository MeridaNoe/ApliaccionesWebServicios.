package mx.edu.utez.personalservice.controller.personal;

import mx.edu.utez.personalservice.model.personal.BeanPersonal;
import mx.edu.utez.personalservice.model.personal.DaoPersonal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api/personal")
public class PersonalServices {

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanPersonal> getAll(){
        return new DaoPersonal().findAll();
    }
}
