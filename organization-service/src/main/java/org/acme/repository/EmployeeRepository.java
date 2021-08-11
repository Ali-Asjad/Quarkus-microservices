package org.acme.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Uni;
import org.acme.model.Employee;

@ApplicationScoped
public class EmployeeRepository {
    
    public List<Employee> employeeSet = new ArrayList<Employee>();

	public List<Employee> getEmployeeSet() {
		return this.employeeSet;
	}

	public void setEmployeeSet(List<Employee> employeeSet) {
		this.employeeSet = employeeSet;
	}


    public EmployeeRepository() {
        // default constructor
    }

    public Employee add (Employee employee) {
        employee.setId((long) employeeSet.size()+1);
        employeeSet.add(employee);
        return employee;
    }

    public Uni<Employee> findById (Long id) {
        Optional<Employee> employee = employeeSet.stream()
                .filter(anEmployee -> anEmployee.getId().equals(id))
                .findFirst();

        if (employee.isPresent()) {
            return Uni.createFrom().item(employee.get());
        }
        else {
            return null;
        }
    }

    public List<Employee> findAllEmployees () {
        return employeeSet;
    }

    public List<Employee> findByOrganization (Long organizationId) {
        return employeeSet.stream()
                .filter(anEmployee -> anEmployee.getOrganizationId().equals(organizationId))
                .collect(Collectors.toList());
    }

}
