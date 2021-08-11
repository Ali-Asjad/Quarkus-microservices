package org.acme.controller;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Uni;
import org.acme.client.DatabaseClient;
import org.acme.model.Employee;
import org.acme.repository.EmployeeRepository;

@Path("/employees")
@ApplicationScoped
public class EmployeeController {

    @Inject
    EmployeeRepository repository;

    @Inject
    @RestClient
    DatabaseClient dbClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Employee> addEmployee (Employee employee) {
        return dbClient.addEmp(employee);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Employee>> getEmployees () {
        return dbClient.getAllEmployees();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Employee> getEmployeeById (@PathParam("id") Long id){
        return repository.findById(id);
    }
}