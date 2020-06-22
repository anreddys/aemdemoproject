package com.aem.demo.core.services.impls;

import java.sql.Connection;

import org.json.simple.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.Constants;
import com.aem.demo.core.dbutils.DatabaseConnectionHelper;
import com.aem.demo.core.jsondatatrasmit.JsonData;
import com.aem.demo.core.service.SightlyServiceInterface;

@Component(immediate=true,service=SightlyServiceInterface.class)
public class SightlyService implements SightlyServiceInterface {
	   private static final Logger log = LoggerFactory.getLogger(SightlyService.class);	
    JsonData obj=new JsonData();	
	@Reference
	private DatabaseConnectionHelper helpr;
	Connection con=null;
	@Override
	public String getDeveloperName() {
		// TODO Auto-generated method stub
		
	 //con=helpr.getDataBaseConnection(Constants.AEM_DATASOURCE);
	 
		return "Allu";
	}

	@Override
	public String getDeveloperProfile() {
		// TODO Auto-generated method stub
		return "AEM Developer";
	}

	@Override
	public String getDeveloperSkills() {
		// TODO Auto-generated method stub
		return "JAVA, OSGI, HTML, JS";
	}

	@Override
	public String getDeveloperData() {
		// TODO Auto-generated method stub
		
		String name = this.getDeveloperName();
		String profile = this.getDeveloperProfile();
		String skills = this.getDeveloperSkills();
		return name + " is a " + profile + ", He is expert in skills like " + skills;
		
		
	}
		
	public JSONObject getJson() {
	log.info("getJson Started ");
		JSONObject jsonObj=obj.getJson();			
		log.info("Json Data from JsonData Class"+jsonObj);		
	   return jsonObj;		
		
	}
}
