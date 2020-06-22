package com.aem.demo.core.wcmusepojos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;


public class TouchMultiFieldPojo extends WCMUsePojo{
	
	private static final Logger log = LoggerFactory.getLogger(TouchMultiFieldPojo.class);
	  
	//Logger log=LoggerFactory.getLogger(this.getClass());
	private HashMap<String,String> links=new HashMap<>();
	@Override
	public void activate() throws Exception {
		String linkpath[]=getProperties().get("linkpath",String[].class);
		String linktitle[]=getProperties().get("linktitle",String[].class);
		for(int i=0;i<linktitle.length;i++) {
			links.put(linktitle[i],linkpath[i]);
		}
		log.info("links: "+links);
	}
	public HashMap<String, String> getLinks() {
		return links;
	}

	
	
		
}
