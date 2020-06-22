package com.aem.demo.core.services.impls;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.Session;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.jackrabbit.JcrConstants;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.json.simple.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NameList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aem.demo.core.bean.Employee;
import com.aem.demo.core.service.ReadXMLFiles;
import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.dam.api.AssetManager;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component(immediate = true, service = ReadXMLFiles.class)
public class ReadXMLFilesImpl implements ReadXMLFiles {

	private static Logger log = LoggerFactory.getLogger(ReadXMLFilesImpl.class);
	private static final String BASE_PATH = "/etc/commerce/products/MasterCatalog";

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;

	ResourceResolver resourceResolver = null;
	private Session session;
	List<Employee> employees = new ArrayList<Employee>();
	String fileName = null;

	@Override
	public String getXMLFiles() {
		// TODO Auto-generated method stub

		log.info("getXMLFiles started");

		try {
			log.info("Inside Try");
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);
			log.info("session ****" + session);
			File folder = new File("C://Users//M1055421//Desktop//xmls");
			log.info("***********afterfile*********");
			File[] listoffiles = folder.listFiles();
			for (int i = 0; i < listoffiles.length; i++) {
				log.info("inside***" + listoffiles.length);
				fileName = folder.toString() + "/" + listoffiles[i].getName();
				log.info("fileName **" + fileName);

			}

		} catch (Exception e) {
			// TODO: handle exception
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.info("errorr ***" + sw.toString());
		} finally {

		}
		return fileName;

	}	

	public List<Employee> parseEmployeesXML() throws ParserConfigurationException, SAXException, IOException {

		try {

			String fileName = getXMLFiles();
			Employee employee = null;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(fileName));
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("employee");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					// Create new Employee Object
					employee = new Employee();
					employee.setId(Integer.parseInt(eElement.getAttribute("id")));
					employee.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
					employee.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
					employee.setLocation(eElement.getElementsByTagName("location").item(0).getTextContent());

					// Add Employee to list
					employees.add(employee);
				}

				log.info("employeesList object ******" + employees);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}

		return employees;
	}

	public void writeToFile() {

		log.info("writeToFile Invoked");

		try {
			List<Employee> employees = parseEmployeesXML();	
			
			 Gson gson = new GsonBuilder().setPrettyPrinting().create();
			 String json = gson.toJson(employees); // converts to json
		      log.info("final converted json****"+json);
		      FileWriter file = new FileWriter("C:\\Users\\M1055421\\xmls\\output.json");
			   file.write(json);
			   file.close();
			   File f = new File(json);
			   log.info("******f***"+f);			   
			   
			  InputStream is = new ByteArrayInputStream(json.getBytes());			  
			   writeToDam(is);		   
			   

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void writeToDam(InputStream is) {
		
		log.info("writeToDam Invoked");
		try {
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
			String filename="output.json";
			String newfile = "/content/dam/test/"+filename;
			ResourceUtil.getOrCreateResource(resourceResolver, BASE_PATH, "sling:folder", "sling:orderedFolder", true);
			log.info("after ResourceUtil ****");
			assetManager.createAsset(newfile, is, "application/json", true);
			resourceResolver.commit();
			log.info("after assetManager end");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			if (null != resourceResolver && resourceResolver.isLive()) {
				resourceResolver.close();
			}

		}

	}

	private Map<String, Object> getSubServiceMap() {
		log.info("*****Inside getSubservice method ****ReadXMLFilesImpl");
		Map<String, Object> serviceMap = null;

		try {

			serviceMap = new HashMap<String, Object>();
			serviceMap.put(ResourceResolverFactory.SUBSERVICE, "readService");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			log.info("errorr ***" + errors.toString());
		}
		log.info("*****getSubservice Method End*****ReadXMLFilesImpl");
		return serviceMap;

	}

}
