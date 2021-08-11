package org.acme.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.PathParam;

import io.smallrye.mutiny.Uni;
import org.acme.model.Employee;
import org.acme.model.Organization;

@RegisterRestClient
public interface DatabaseClient {
    
    @GET
    @Path("/organizations")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Organization>> getAllOrganizations();

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Employee>> getAllEmployees();

    @GET
    @Path("/employees/organization/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Employee>> getEmpByOrgId(@PathParam("id") Long id);

    @POST
    @Path("/employees/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Employee> addEmp(Employee employee);
}
