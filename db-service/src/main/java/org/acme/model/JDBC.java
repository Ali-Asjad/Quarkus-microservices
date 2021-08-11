package org.acme.model;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;

import java.util.List;
import java.util.stream.StreamSupport;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JDBC {


    public static Uni<List<Employee>> findAllEmployee (MySQLPool client) {
        return client.query("SELECT * FROM Employees").execute()
                .onItem().transformToMulti(rowSet -> Multi.createFrom().items(() -> StreamSupport.stream(rowSet.spliterator(), false)))
                .onItem().transform(Employee::fromRow)
                .collect().asList();
    }

    public static Uni<List<Organization>> findAllOrganization (MySQLPool client) {
        return client.query("SELECT * FROM Organization").execute()
                .onItem().transformToMulti(rowSet -> Multi.createFrom().items(() -> StreamSupport.stream(rowSet.spliterator(), false)))
                .onItem().transform(Organization::fromRow)
                .collect().asList();
    }

    public static Uni<List<Employee>> findEmpByOrgId (MySQLPool client, Long id) {
        return client.query("SELECT * FROM Employees WHERE OrganizationId = " + id).execute()
                .onItem().transformToMulti(rowSet -> Multi.createFrom().items(() -> StreamSupport.stream(rowSet.spliterator(), false)))
                .onItem().transform(Employee::fromRow)
                .collect().asList();
    }

    public static Uni<Employee> findEmpById (MySQLPool client, Long id) {
        return client.query("SELECT * FROM Employees WHERE id = " + id).execute()
                .onItem().transform(i -> i.iterator().hasNext() ? Employee.fromRow(i.iterator().next()) : null);
    }

    public static Uni<Employee> addEmployee (MySQLPool client, Employee employee) {
        return client.query("INSERT INTO Employees (id, Name, Age, Position, OrganizationId) VALUES ('" + employee.getId() + "', '" + employee.getName() + "', '" + employee.getAge() + "', '" + employee.getPosition() + "', '" + employee.getOrganizationId() + "')").execute()
                .onItem().transform(i -> i.iterator().hasNext() ? Employee.fromRow(i.iterator().next()) : null);
    }
    
}
