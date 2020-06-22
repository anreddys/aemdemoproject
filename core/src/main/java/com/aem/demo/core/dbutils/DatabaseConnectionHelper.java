package com.aem.demo.core.dbutils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.day.commons.datasource.poolservice.DataSourcePool;


@Component(immediate=true,service=DatabaseConnectionHelper.class)
public class DatabaseConnectionHelper {
	
	
	protected static final Logger log=LoggerFactory.getLogger(DatabaseConnectionHelper.class);
		
	@Reference
	private DataSourcePool dataSourceService;
	Connection con=null;
	
	public Connection getDataBaseConnection(String dataSourceName) {
		
		log.info("getDataBase Started *****");
		
		try {
			DataSource dataSource=(DataSource) dataSourceService.getDataSource(dataSourceName);
			con=dataSource.getConnection();
			log.info("MySql Database Connection Successfully Established "+con);
		} catch (Exception e) {
			
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
			
		}
				
		return con;
		
	}

}
