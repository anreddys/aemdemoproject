package com.aem.demo.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aem.demo.core.bean.Employee;

public interface ReadXMLFiles {
	
	public String getXMLFiles();
	public List<Employee> parseEmployeesXML() throws ParserConfigurationException, SAXException, IOException;
	public void writeToFile();
	public void writeToDam(InputStream is);
	

}
