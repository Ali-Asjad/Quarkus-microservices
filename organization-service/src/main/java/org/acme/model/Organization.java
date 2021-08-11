package org.acme.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    
    private Long id;
    private String name;
    private String address;
    private List<Employee> employees = new ArrayList<>();

	public Organization () {
		// default constructor
	}

	public Organization (String name, String address) {
		this.name = name;
		this.address = address;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
