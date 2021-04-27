package org.eno.bot.backend;


import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;


@RequestScoped
@Path("/hello")
public class HelloWorldEndpoint {


    @GET
    @Produces("text/plain")
    public Response doGet() {

        return Response.ok("Hello from WildFly bootable jar!").build();
    }
}
