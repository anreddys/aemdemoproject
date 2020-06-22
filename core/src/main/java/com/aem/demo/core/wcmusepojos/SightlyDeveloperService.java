package com.aem.demo.core.wcmusepojos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.demo.core.service.SightlyServiceInterface;

public class SightlyDeveloperService extends WCMUsePojo {

	
	Logger logger = LoggerFactory.getLogger(SightlyDeveloperService.class);
//	@Override
	
	protected String details;
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
				
       logger.info("inside activate method of Developer use pojo");	
		
		SightlyServiceInterface SightlyServiceInterface=  getSlingScriptHelper().getService(SightlyServiceInterface.class);
		    details = SightlyServiceInterface.getDeveloperData();
		
	}
	
}
