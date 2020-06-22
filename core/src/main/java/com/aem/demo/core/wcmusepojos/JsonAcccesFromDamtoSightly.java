package com.aem.demo.core.wcmusepojos;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.simple.JSONObject;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.demo.core.bean.TestBean;
import com.aem.demo.core.service.DamJsonOsgiServiceSightlyUsingWcmUsePojo;
import com.aem.demo.core.service.SightlyServiceInterface;
import com.day.cq.dam.api.Asset;



public class JsonAcccesFromDamtoSightly  extends WCMUsePojo{

	private static final Logger log = LoggerFactory.getLogger(JsonAcccesFromDamtoSightly.class);	
	
	private String json;
	@Override
	public void activate() throws Exception {
				
		log.info("JsonAcccesFromDamtoSightly Using Wcmpojo Started");
		
		DamJsonOsgiServiceSightlyUsingWcmUsePojo objss=  getSlingScriptHelper().getService(DamJsonOsgiServiceSightlyUsingWcmUsePojo.class);
	    json = objss.getjsonObjcect();
	   
	    JSONObject jsonObj = new JSONObject();
	     
	   /* JSONObject json = new JSONObject(obj);
        JSONArray result = json.getJSONArray("results");
        JSONObject result1 = result.getJSONObject(0);
        JSONObject geometry = result1.getJSONObject("geometry");
        JSONObject locat = geometry.getJSONObject("location");*/

        //"iterate onto level of location";

       /* double lat = locat.getDouble("lat");
        double lng = locat.getDouble("lng");*/
	    
	 log.info("Final Json Object"+json);

     }
	public String getJson() {
		return json;
	}
	
}
