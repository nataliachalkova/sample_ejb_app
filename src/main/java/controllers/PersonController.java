package controllers;

import model.Person;
import services.PersonService;
import services.PersonValidationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/person")
public class PersonController {

    @Inject
    private PersonService userService;
    @Inject
    private PersonValidationService validationService;

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean save(Person p) {
        boolean result = false;
        if (validationService.validate(p)) {
            if (p.getId() < 0) {
                result = userService.savePerson(p);
            } else {
                if (validationService.validate(Integer.toString(p.getId()))) {
                    result = userService.editPerson(p);
                }
            }
        }
        return result;
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person get(@PathParam("id") String id) {
        Person p = null;
        if (validationService.validate(id)) {
            p = userService.getPerson(Integer.parseInt(id));
        }
        return p;
    }
}
