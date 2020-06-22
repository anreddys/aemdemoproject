package com.aem.demo.core.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExcelReadExample {
	static String json=null;
	 public static void main(String[] args) {
	        // An excel file name. You can create a file name with a full
	        // path information.
	        String filename = "C://Users//M1055421//temp/Employee.xlsx";

	        // Create an ArrayList to store the data read from excel sheet.
	        List<List<XSSFCell>> sheetData = new ArrayList<>();

	        try  {
	        	FileInputStream fis = new FileInputStream(filename);
	            // Create an excel workbook from the file system.
	            XSSFWorkbook workbook = new XSSFWorkbook(fis);
	            // Get the first sheet on the workbook.
	            XSSFSheet sheet = workbook.getSheetAt(0);	            
	            Iterator rows = sheet.rowIterator();
	            while (rows.hasNext()) {
	            	
	                XSSFRow row = (XSSFRow) rows.next();
	                
	                XSSFCell cel=row.getCell(0);
	               // System.out.println("ID -------"+cel);
	                XSSFCell cel1=row.getCell(1);
	               // System.out.println("Name -------"+cel1);
	                XSSFCell cel2=row.getCell(2);
	               // System.out.println("salary --------"+cel2);
	                XSSFCell cel3=row.getCell(3);
	               // System.out.println("Department -------"+cel3);
	                XSSFCell cel4=row.getCell(4);
	               // System.out.println("Manager -------"+cel4);
	               
	                Employ emp=new Employ();
	                emp.setId(cel.toString());
	                emp.setName(cel1.toString());
	                emp.setSalary(cel2.toString());
	                emp.setDept(cel3.toString());
	                emp.setManager(cel4.toString());
	                
	                
	                ArrayList<Employ> retail=new ArrayList<Employ>();
	               
	                retail.add(emp);
	                show(retail);
	                
	               // System.out.println("******************"+emp.toString());
	                
	                
	               // List<Employee> employees = parseEmployeesXML();	
	    			
	   			/* Gson gson = new GsonBuilder().setPrettyPrinting().create();
	   			 json = gson.toJson(retail); // converts to json
*/	   		     /* FileWriter file = new FileWriter("C:\\Users\\M1055421\\excels\\output.json");
	   			   file.write(json);
	   			   file.close();
	   			   File f = new File(json);
	   			   System.out.println("******f***"+f);			   
	   			   
	   			  InputStream is = new ByteArrayInputStream(json.getBytes());
	                */
	                
	                
	                
	                
	                
	                Iterator cells = row.cellIterator();

	                List<XSSFCell> data = new ArrayList<>();
	                while (cells.hasNext()) {
	                    XSSFCell cell = (XSSFCell) cells.next();
	                    data.add(cell);
	                }
	                sheetData.add(data);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        showExcelData(sheetData);
	    }
	 
	 private static void show(List<Employ> sheetData) {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
			 json = gson.toJson(sheetData); // converts to json
			 System.out.println("final converted json****"+json);
			 FileWriter file;
			try {
				file = new FileWriter("C:\\Users\\M1055421\\excels\\output.json");
				 file.write(json);
				   file.close();
				   File f = new File(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		 
		 
		 
	 }

	    private static void showExcelData(List<List<XSSFCell>> sheetData) {
	        // Iterates the data and print it out to the console.
	        for (List<XSSFCell> data : sheetData) {
	            for (XSSFCell cell : data) {
	               // System.out.println("*****************"+cell.getStringCellValue());
	              
	               
	                //List<Employee> employees = parseEmployeesXML();	
	    			
	   			/* Gson gson = new GsonBuilder().setPrettyPrinting().create();
	   			 String json = gson.toJson(data); // converts to json
	   		      System.out.println("final converted json****"+json);
	   		      FileWriter file;
				try {
					file = new FileWriter("C:\\Users\\M1055421\\excels\\output.json");
					 file.write(json);
		   			   file.close();
		   			   File f = new File(json);
		   			   System.out.println("******f***"+f);			   
		   			   
		   			  InputStream is = new ByteArrayInputStream(json.getBytes());	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   			  	
	                
	                */
	                
	                
	                
	            }
	        }
	    }

}
