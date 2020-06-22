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

import javax.jcr.Node;
import javax.jcr.RepositoryException;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.sling.api.resource.Resource;
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

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aem.demo.core.bean.Employ;
import com.aem.demo.core.service.ReadExcelFiles;
import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.dam.api.AssetManager;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

                   

@Component(immediate = true, service = ReadExcelFiles.class)
public class ReadExcelFilesImpl implements ReadExcelFiles {

	private static Logger log = LoggerFactory.getLogger(ReadExcelFilesImpl.class);
	private static final String BASE_PATH = "/etc/commerce/products/MasterCatalog";

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;

	ResourceResolver resourceResolver = null;
	private Session session;
	String fileName = null;
	InputStream inputStream = null;

	/*
	 * List<Employee> employees = new ArrayList<Employee>(); String fileName = null;
	 * 
	 * @Override public String getXMLFiles() { // TODO Auto-generated method stub
	 * 
	 * log.info("getXMLFiles started");
	 * 
	 * try { log.info("Inside Try"); resourceResolver =
	 * resolverFactory.getServiceResourceResolver(getSubServiceMap()); session =
	 * resourceResolver.adaptTo(Session.class); log.info("session ****" + session);
	 * File folder = new File("C://Users//M1055421//Desktop//xmls");
	 * log.info("***********afterfile*********"); File[] listoffiles =
	 * folder.listFiles(); for (int i = 0; i < listoffiles.length; i++) {
	 * log.info("inside***" + listoffiles.length); fileName = folder.toString() +
	 * "/" + listoffiles[i].getName(); log.info("fileName **" + fileName);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { // TODO: handle exception StringWriter sw = new
	 * StringWriter(); e.printStackTrace(new PrintWriter(sw)); log.info("errorr ***"
	 * + sw.toString()); } finally {
	 * 
	 * } return fileName;
	 * 
	 * }
	 * 
	 * public List<Employee> parseEmployeesXML() throws
	 * ParserConfigurationException, SAXException, IOException {
	 * 
	 * try {
	 * 
	 * String fileName = getXMLFiles(); Employee employee = null;
	 * 
	 * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder builder = factory.newDocumentBuilder(); Document document =
	 * builder.parse(new File(fileName)); document.getDocumentElement().normalize();
	 * NodeList nList = document.getElementsByTagName("employee"); for (int temp =
	 * 0; temp < nList.getLength(); temp++) { Node node = nList.item(temp); if
	 * (node.getNodeType() == Node.ELEMENT_NODE) { Element eElement = (Element)
	 * node; // Create new Employee Object employee = new Employee();
	 * employee.setId(Integer.parseInt(eElement.getAttribute("id")));
	 * employee.setFirstName(eElement.getElementsByTagName("firstName").item(0).
	 * getTextContent());
	 * employee.setLastName(eElement.getElementsByTagName("lastName").item(0).
	 * getTextContent());
	 * employee.setLocation(eElement.getElementsByTagName("location").item(0).
	 * getTextContent());
	 * 
	 * // Add Employee to list employees.add(employee); }
	 * 
	 * log.info("employeesList object ******" + employees); }
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * finally {
	 * 
	 * }
	 * 
	 * return employees; }
	 * 
	 * public void writeToFile() {
	 * 
	 * log.info("writeToFile Invoked");
	 * 
	 * try { List<Employee> employees = parseEmployeesXML();
	 * 
	 * Gson gson = new GsonBuilder().setPrettyPrinting().create(); String json =
	 * gson.toJson(employees); // converts to json
	 * log.info("final converted json****"+json); FileWriter file = new
	 * FileWriter("C:\\Users\\M1055421\\xmls\\output.json"); file.write(json);
	 * file.close(); File f = new File(json); log.info("******f***"+f);
	 * 
	 * InputStream is = new ByteArrayInputStream(json.getBytes()); writeToDam(is);
	 * 
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public void writeToDam(InputStream is) {
	 * 
	 * log.info("writeToDam Invoked"); try { resourceResolver =
	 * resolverFactory.getServiceResourceResolver(getSubServiceMap()); AssetManager
	 * assetManager = resourceResolver.adaptTo(AssetManager.class); String
	 * filename="output.json"; String newfile = "/content/dam/test/"+filename;
	 * ResourceUtil.getOrCreateResource(resourceResolver, BASE_PATH, "sling:folder",
	 * "sling:orderedFolder", true); log.info("after ResourceUtil ****");
	 * assetManager.createAsset(newfile, is, "application/json", true);
	 * resourceResolver.commit(); log.info("after assetManager end");
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * finally {
	 * 
	 * if (null != resourceResolver && resourceResolver.isLive()) {
	 * resourceResolver.close(); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * private Map<String, Object> getSubServiceMap() {
	 * log.info("*****Inside getSubservice method ****ReadXMLFilesImpl");
	 * Map<String, Object> serviceMap = null;
	 * 
	 * try {
	 * 
	 * serviceMap = new HashMap<String, Object>();
	 * serviceMap.put(ResourceResolverFactory.SUBSERVICE, "readService");
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace();
	 * StringWriter errors = new StringWriter(); e.printStackTrace(new
	 * PrintWriter(errors)); log.info("errorr ***" + errors.toString()); }
	 * log.info("*****getSubservice Method End*****ReadXMLFilesImpl"); return
	 * serviceMap;
	 * 
	 * }
	 */
	@Override
	public InputStream getExcelFiles() {
		// TODO Auto-generated method stub

		try {

			log.info("Inside Try");
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);
			log.info("session ****" + session);
			/*File folder = new File("C://Users//M1055421//Desktop//excels");
			log.info("***********afterfile*********");
			File[] listoffiles = folder.listFiles();
			for (int i = 0; i < listoffiles.length; i++) {
				log.info("inside***" + listoffiles.length);
				fileName = folder.toString() + "/" + listoffiles[i].getName();
				log.info("fileName **" + fileName);
				inputStream = new FileInputStream(fileName);
				injectSpreadSheet(inputStream);

			}*/
			
			 String fileName="C://Users//M1055421//temp/Employee.xlsx";
			// inputStream = new FileInputStream(fileName);
				//injectSpreadSheet(inputStream);
			 readBooksFromExcelFile(fileName);

		} catch (Exception e) {
			// TODO: handle exception
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.info("errorr ***" + sw.toString());
		} finally {

		}

		return null;
	}
	
	
	public List<Employ> readBooksFromExcelFile(String excelFilePath) throws IOException {
		log.info("readBooksFromExcelFile Invoked");
		log.info("excelFilePath ***"+excelFilePath);
	    List<Employ> listBooks = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	    
	    XSSFSheet firstSheet = workbook.getSheetAt(0);
	    log.info("firstSheet ****"+firstSheet);
	    
	    Iterator<Row> iterator = firstSheet.iterator();
	    
	    log.info("iterator ****"+iterator);
	    
	    //Find number of rows in excel file
	    int rowcount = firstSheet.getLastRowNum()- firstSheet.getFirstRowNum();
	    log.info(("Total row number: "+rowcount));
	 
	    for(int i=1; i<rowcount+1; i++){
	        //Create a loop to get the cell values of a row for one iteration
	        Row row = firstSheet.getRow(i);
	        List<String> arrName = new ArrayList<String>();
	        for(int j=0; j<row.getLastCellNum(); j++){
	            // Create an object reference of 'Cell' class
	            Cell cell = row.getCell(j);
	            // Add all the cell values of a particular row
	            arrName.add(cell.getStringCellValue());
	            }
	 
	        log.info("arrName************"+arrName);
	   /* while (iterator.hasNext()) {
	    	log.info("inside first while");
	        Row nextRow = iterator.next();
	        log.info("*****nextrow"+nextRow);
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        log.info("cellIterator ****"+cellIterator);
	        Employ aBook = new Employ();
	 
	        while (cellIterator.hasNext()) {
	        	log.info("secnd while ");
	        	
	            Cell nextCell = cellIterator.next();
	            log.info("***********"+nextCell);
	           /* int columnIndex = nextCell.getColumnIndex();
	            log.info("@@@@@@@@@@@@@"+columnIndex);
	            
	 
	            switch (columnIndex) {
	            case 1:
	                aBook.setId((String) getCellValue(nextCell));
	                break;
	            case 2:
	                aBook.setName((String) getCellValue(nextCell));
	                break;
	            case 3:
	                aBook.setSalary((String) getCellValue(nextCell));
	                break;
	            case 4:
	                aBook.setDept((String) getCellValue(nextCell));
	                break;
	            case 5:
	                aBook.setManager((String) getCellValue(nextCell));
	                break;
	            }*/
	            
	            
	          /*  switch (nextCell.getCellType())
				 { case Cell.CELL_TYPE_STRING:
					// System.out.print(nextCell.getStringCellValue() + "\t");
					 log.info("***************fir**"+nextCell.getRichStringCellValue().getString());
					 aBook.setId(nextCell.getRichStringCellValue().getString());
				 break;
				 case Cell.CELL_TYPE_NUMERIC:
					// System.out.print(nextCell.getNumericCellValue() + "\t");
					 log.info("***************secnd**"+nextCell.getStringCellValue());
					 aBook.setName(nextCell.getRichStringCellValue().getString());
				 break;
				 case Cell.CELL_TYPE_BOOLEAN: 
					// System.out.print(nextCell.getBooleanCellValue() + "\t"); 
					 log.info("***************third**"+nextCell.getStringCellValue());
					 aBook.setSalary(nextCell.getRichStringCellValue().getString());
				 break; 
				 default: 
				} */
	 
	 
	     //   }
	      //  listBooks.add(aBook);
	       // log.info("employees data ****** "+listBooks);
	  //  }
	 
	   // workbook.close();
	   // inputStream.close();
	 
	   return null;
	}
		return listBooks;
	}
	
	
	
	
	

	private String getCellValue(Cell nextCell) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int injectSpreadSheet(InputStream is) {
		// TODO Auto-generated method stub

		try {

			// Get the Spread sheet
		/*	Workbook workbook = Workbook.getWorkbook(is);
			log.info("workbook *****"+workbook);
			Sheet sheet = workbook.getSheet(0);
			log.info("sheet*****"+sheet);
			String firstName = "";
			String lastName = "";
			String address = "";
			String desc = "";*/
			
			
			InputStream XlsxFileToRead=null;
	    	XSSFWorkbook workbook=null;    
	    	
	    	XlsxFileToRead=new FileInputStream(fileName);
    		log.info("XlsxFileRead *******************"+XlsxFileToRead);    		
    		//Getting the workbook instance for xlsx file    		
			workbook=new XSSFWorkbook(XlsxFileToRead);			
			//log.info("workbook*************"+workbook);
			
			XSSFSheet sheet=workbook.getSheet("Sheet1"); 
	    	log.info("sheet ****"+sheet);
	    	

			/*for (int index = 0; index < 4; index++) {
				Cell a3 = sheet.getCell(0, index + 2);
				Cell b3 = sheet.getCell(1, index + 2);
				Cell c3 = sheet.getCell(2, index + 2);
				Cell d3 = sheet.getCell(3, index + 2);

				firstName = a3.getContents();
				lastName = b3.getContents();
				address = c3.getContents();
				desc = d3.getContents();
				// Store the excel data into the Adobe AEM JCR
			}
			injectCustomerData(firstName, lastName, address, desc);*/

		} catch (Exception e) {
			// TODO: handle exception
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.info("errorr ***" + sw.toString());
		} finally {

		}
		return 0;
	}

	@Override
	public int injectCustomerData(String firstName, String lastName, String address, String desc) {
		// TODO Auto-generated method stub
		log.info("injectCustomerData Invoked");
		int custId = 0;
		try {
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);
			log.info("session ****" + session);

			// Create a Node that represents the root node
			Node root = session.getRootNode();
			log.info("root Node ***"+root);
			
			// Get the content Node in JCR
			Node content = root.getNode("content");
			
			log.info("content ***"+content);
			// Determine if the content/customer node exists
			Node customerRoot = null;
			// String resourcePath="/content/customerexcel/customer";
			int custRec = doesCustomerExist(content);

			log.info("****Value of custRec is...." + custRec);

			// -1 means that content/customer does not exist
			if (custRec == -1) {
				customerRoot = content.addNode("customerexcel");
				log.info("inside -1 ****");

			} else {
				// content/customer does exist ....retrieve it
				customerRoot = content.getNode("customerexcel");
				log.info("inside 1");

			}
			
			log.info("outside ****");
			custId = custRec + 1; // assign a new id to the customer node

			// store content from the file to in the jcr
			Node custNode = customerRoot.addNode("customer" + firstName + lastName + custId, "nt:unstructured");
			log.info("custNode ****"+custNode);
			// Make sure the the name of node is Unique

			custNode.setProperty("id", custId);
			custNode.setProperty("firstName", firstName);
			custNode.setProperty("lastName", lastName);
			custNode.setProperty("address", address);
			custNode.setProperty("desc", desc);

			// Save the sessoin changes and logout
			session.save();
			session.logout();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}
		log.info("**********************************custId****"+custId);
		return custId;
	}

	public int doesCustomerExist(Node content) {
		log.info("doesCustomerExist ");

		int i = 0;

		try {
			if (content.hasNode("customer")) {
				i = 1;
			} else {
				i = -1;
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		{

		}
		return i;

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
