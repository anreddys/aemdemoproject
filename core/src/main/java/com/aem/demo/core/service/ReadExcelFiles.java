package com.aem.demo.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aem.demo.core.bean.Employee;
import com.aem.demo.core.bean.Product;

public interface ReadExcelFiles {
	public InputStream getExcelFiles();
	public int injectSpreadSheet(InputStream is);
	public int injectCustomerData(String firstName, String lastName,String address,String desc);
	

}
