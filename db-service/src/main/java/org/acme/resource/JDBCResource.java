package org.acme.resource;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import org.acme.model.Employee;
import org.acme.model.JDBC;
import org.acme.model.Organization;

@Path("/jdbc")
public class JDBCResource {

    private final MySQLPool client;
    
    public JDBCResource(MySQLPool client) {
        this.client = client;
    }

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Employee>> getAllEmployees () {
        return JDBC.findAllEmployee(client);
    }

    @GET
    @Path("/organizations")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Organization>> getAllOrganizations () {
        return JDBC.findAllOrganization(client);
    }

    @GET
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Employee> getEmpById (@PathParam("id") Long id) {
        return JDBC.findEmpById(client, id);
    }

    @GET
    @Path("/employees/organization/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Employee>> getEmpByOrgId (@PathParam("id") Long id) {
        return JDBC.findEmpByOrgId(client, id);
    }

    @POST
    @Path("/employees/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Employee> addEmp (Employee employee) {
        return JDBC.addEmployee(client, employee);
    }
}
