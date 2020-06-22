package com.aem.demo.core.servlets;



import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.models.UserModel;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Sling Demo Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/slingmodel/user" })
public class SlingModelServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 Logger log = LoggerFactory.getLogger(this.getClass());
	    @Reference
	    ResourceResolverFactory resourceResolverFactory;    
	    ResourceResolver resourceResolver;
	    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException,IOException{
	        log.info("inside sling model test servlet");
	        //response.setContentType("text/html");
	        
	        
	        /**
			 * Getting the instance of resource resolver
			 */
		ResourceResolver resourceResolver = request.getResourceResolver();
	        
	       // HashMap<String, Object> param = new HashMap<>();
			//param.put(ResourceResolverFactory.SUBSERVICE, "readService"); // readService is my System User.
	        try {
	        	//resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);			
	            Resource resource = resourceResolver.getResource("/content/testsling/slingmodel");
	            log.info("resource *****" + resource);
	            ValueMap valueMap=resource.adaptTo(ValueMap.class);
	             
	            response.getWriter().write("Output from ValueMap is First Name: "+valueMap.get("firstName").toString()+" Last Name: "+valueMap.get("lastName").toString()+" Gender: "+valueMap.get("gender").toString()+"Country :"+valueMap.get("country").toString());
	             
	                        
							UserModel userInfo = resource.adaptTo(UserModel.class);
	            response.getWriter().write("Output from Sling Model is First Name: "+userInfo.getFirstName()+" Last Name: "+userInfo.getLastName()+" gender: "+userInfo.getGender()+" country :"+userInfo.getCountry());
	                     
	        } catch (Exception e) {
	            log.error(e.getMessage());
	        }
	        finally{
	            if(resourceResolver.isLive())
	                resourceResolver.close();
	        }
	         
	     
	    }
	
	

}
