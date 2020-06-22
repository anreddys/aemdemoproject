/*package com.aem.demo.core.services.impls;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo {
	
	public static void main(String[] args) {
		
		
		
		try {
			File excel = new File("C://Users//M1055421//temp/Employee.xlsx"); 
			System.out.println("****excel**"+excel);
			FileInputStream fis = new FileInputStream(excel);
			System.out.println("fis*****"+fis);
			 XSSFWorkbook book = new XSSFWorkbook(fis);
			 System.out.println("******book***"+book);
			 XSSFSheet sheet = book.getSheetAt(0); 
			 System.out.println("sheet ***"+sheet);
			Iterator<Row> itr = sheet.iterator();
			System.out.println("itr*****"+itr);

			// Iterating over Excel file in Java
			 while (itr.hasNext())
			 { 
			  Row row = itr.next(); // Iterating over each column of Excel file
			 Iterator<Cell> cellIterator = row.cellIterator();
             System.out.println("******&&&***"+cellIterator);
			 
			while (cellIterator.hasNext())
			 {
				Cell cell = cellIterator.next(); 
				System.out.println("celliterator ******"+cell);
			  switch (cell.getCellType())
			 { case Cell.CELL_TYPE_STRING:
				 System.out.print(cell.getStringCellValue() + "\t");
			 break;
			 case Cell.CELL_TYPE_NUMERIC:
				 System.out.print(cell.getNumericCellValue() + "\t");
			 break;
			 case Cell.CELL_TYPE_BOOLEAN: 
				 System.out.print(cell.getBooleanCellValue() + "\t"); 
			 break; 
			 default: 
			} 
			}
			 System.out.println(""); 
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}
		
	
		
		public List<Book> readBooksFromExcelFile(String excelFilePath) throws IOException {
		    List<Book> listBooks = new ArrayList<>();
		    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		 
		    Workbook workbook = new XSSFWorkbook(inputStream);
		    Sheet firstSheet = workbook.getSheetAt(0);
		    Iterator<Row> iterator = firstSheet.iterator();
		 
		    while (iterator.hasNext()) {
		        Row nextRow = iterator.next();
		        Iterator<Cell> cellIterator = nextRow.cellIterator();
		        Book aBook = new Book();
		 
		        while (cellIterator.hasNext()) {
		            Cell nextCell = cellIterator.next();
		            int columnIndex = nextCell.getColumnIndex();
		 
		            switch (columnIndex) {
		            case 1:
		                aBook.setTitle((String) getCellValue(nextCell));
		                break;
		            case 2:
		                aBook.setAuthor((String) getCellValue(nextCell));
		                break;
		            case 3:
		                aBook.setPrice((double) getCellValue(nextCell));
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
}*/