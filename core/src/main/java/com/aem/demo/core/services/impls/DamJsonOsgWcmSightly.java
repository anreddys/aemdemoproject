package com.aem.demo.core.services.impls;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.simple.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.service.DamJsonOsgiServiceSightlyUsingWcmUsePojo;
import com.aem.demo.core.service.PageService;
import com.aem.demo.core.service.SightlyServiceInterface;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true, service = DamJsonOsgiServiceSightlyUsingWcmUsePojo.class)
public class DamJsonOsgWcmSightly implements DamJsonOsgiServiceSightlyUsingWcmUsePojo {

	private static Logger log = LoggerFactory.getLogger(DamJsonOsgWcmSightly.class);
	//private String user = "";

	//private Session session;

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;
	ResourceResolver resourceResolver = null;
	Resource original;
	String myJSON = "";
	@Override
	public String getjsonObjcect() {
		// TODO Auto-generated method stub
		log.info("*****getjsonObjcect Method Started *****");
		try {
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			Resource resource = resourceResolver.getResource("/content/dam/test/sample");
			log.info("resource *****" + resource);
			Asset asset = resource.adaptTo(Asset.class);
			original = asset.getOriginal();
			log.info("original *****" + original);
			InputStream content = original.adaptTo(InputStream.class);
			log.info("content *****" + content);
			StringBuilder sb = new StringBuilder();
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));

			while ((line = br.readLine()) != null) {
				sb.append(line);
				
			}
			log.info("json object from DamJsonOsgWcmSightly  ::" + sb.toString());
		myJSON = sb.toString();
		    
		
		
		
			JSONObject jsonObj = new JSONObject(); // In jsonObj I will get the data from the JSON file from DAM.
		
			
			
		} catch (Exception e) {			
			      // TODO: handle exception
						StringWriter sw = new StringWriter();
						e.printStackTrace(new PrintWriter(sw));
						log.error("**error from **" + sw.toString());
		}finally {
			log.info("finally called");
			
		}
		
		return myJSON;
	}
	
	
	
	// Create the user service map

	private Map<String, Object> getSubServiceMap() {
		log.info("*****Inside getSubservice method ****");
		Map<String, Object> serviceMap = null;

		try {

			serviceMap = new HashMap<String, Object>();
			serviceMap.put(ResourceResolverFactory.SUBSERVICE, "readService");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
		}
		log.info("*****getSubservice Method End*****");
		return serviceMap;

	}
}
