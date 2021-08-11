package org.acme.controller;

import org.acme.client.DatabaseClient;
import org.acme.model.Employee;
import org.acme.model.Organization;
import org.acme.repository.EmployeeRepository;
import org.acme.repository.OrganizationRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

@Path("/organizations")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OrganizationController {

    @Inject
    EmployeeRepository EmpRepository;

    @Inject
    OrganizationRepository repository;

    @Inject
    @RestClient
    DatabaseClient dbClient;

    @POST
    public Uni<Organization> add(Organization organization) {
        repository.add(organization);
        return Uni.createFrom().item(organization);
    }

    @GET
    public Uni<List<Organization>> getAllOrg () {
        return dbClient.getAllOrganizations();
    }

    @GET
    @Path("/{id}")
    public Uni<Organization> getById (@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @GET
    @Path("/employees/{id}")
    @Blocking
    public Uni<List<Employee>> getByOrgId (@PathParam("id") Long id) {
        return dbClient.getEmpByOrgId(id);
    }
}