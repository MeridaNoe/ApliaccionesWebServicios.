package mx.edu.utez.personalservice.controller.personal;

import mx.edu.utez.personalservice.model.personal.BeanPersonal;
import mx.edu.utez.personalservice.model.personal.DaoPersonal;
import mx.edu.utez.personalservice.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/api/personal")
public class PersonalServices {

    @GET //metodo http obtiene datos
    @Path("/") // La forma de Identificar un recurso (clase, Metodo)
    @Produces(value = {"application/json"}) //
    public List<BeanPersonal> getAll(){
        return new DaoPersonal().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON) // Convierte la respuesta en JSON ("{}")
    public Response<BeanPersonal> getById(@PathParam("id") Long id){
        return new DaoPersonal().findById(id);
    }

    @POST // insercion
    @Path("/") // "/api/personal/"
    @Consumes(MediaType.APPLICATION_JSON)// consume json
    @Produces(MediaType.APPLICATION_JSON)// (retorna json) recibimos un objeto de una persona en formato json
    public Response<BeanPersonal> save(BeanPersonal person){
        return new DaoPersonal().save(person);
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)// consume json
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanPersonal> update(BeanPersonal person){
        return new DaoPersonal().update(person);
    }
}
