/*package com.aem.demo.core.services.impls;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aem.demo.core.bean.Employee;

public class Test {
	//static String excelFilePath=null; 
	public static void main(String[] args) {
		//File excelFilePath = new File("C://Users//M1055421//temp/Employee.xlsx"); 
		//excelFilePath="C://Users//M1055421//temp/Employee.xlsx";
		List<Employee> readBooksFromExcelFile() throws IOException {
			String excelFilePath="C://Users//M1055421//temp/Employee.xlsx";
		    List<Employee> listBooks = new ArrayList<>();
		    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		 
		    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		    XSSFSheet firstSheet = workbook.getSheetAt(0);
		    Iterator<Row> iterator = firstSheet.iterator();
		 
		    while (iterator.hasNext()) {
		        Row nextRow = iterator.next();
		        Iterator<Cell> cellIterator = nextRow.cellIterator();
		        Employee aBook = new Employee();
		 
		        while (cellIterator.hasNext()) {
		            Cell nextCell = cellIterator.next();
		            int columnIndex = nextCell.getColumnIndex();
		 
		            switch (columnIndex) {
		            case 1:
		                aBook.setId((String) getCellValue(nextCell));
		                break;
		            case 2:
		                aBook.setFirstName((String) getCellValue(nextCell));
		                break;
		            case 3:
		                aBook.setLastName((String) getCellValue(nextCell));
		                break;
		            case 4:
		                aBook.setLocation((String) getCellValue(nextCell));
		                break;
		            }
		 
		 
		        }
		        listBooks.add(aBook);
		    }
		 
		    workbook.close();
		    inputStream.close();
		 
		    return listBooks;
		}


		
		
	}

}
*/