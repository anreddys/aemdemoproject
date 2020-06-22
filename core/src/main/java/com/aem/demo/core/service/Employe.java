package com.aem.demo.core.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.aem.demo.core.bean.Employees;

public interface Employe {
	
	public boolean registerEmployeeDetails(Employees emps);	
	public List<Employees> getAllEmployees();
	public boolean loginEmploye();
	public boolean updateEmployee(String id, String name, String salary,String depart);
	public boolean deleteEmployee(String id);
	public JSONObject getEmployeDetails();
	
	

}
