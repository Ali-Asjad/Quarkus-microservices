package org.acme.model;


import io.vertx.mutiny.sqlclient.Row;

public class Employee {
    private Long id;
    private Long organizationId;
    private String name;
    private int age;
    private String position;

    public Employee () {
        // default constructor
    }

    public Employee (Long id, Long organizationId, String name, int age, String position) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
        this.age = age;
        this.position = position;
    }
	

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

    @Override
    public String toString() {
        return "Employee [id=" + id + ", organizationId=" + organizationId
                + ", name=" + name + ", position=" + position + "]";
    }

	public static Employee fromRow(Row row) {
		return new Employee (row.getLong("id"), row.getLong("OrganizationId"), row.getString("Name"), row.getInteger("Age"), row.getString("Position"));
	}
    
}
