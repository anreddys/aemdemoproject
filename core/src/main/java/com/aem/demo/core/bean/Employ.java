package com.aem.demo.core.bean;

public class Employ {
	
	private String id;
	private String name;
	private String salary;
	private String dept;
	private String manager;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "Employ [id=" + id + ", name=" + name + ", salary=" + salary + ", dept=" + dept + ", manager=" + manager
				+ "]";
	}
	
	

}
