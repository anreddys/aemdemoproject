
package com.aem.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.simple.JSONObject;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.dbutils.DatabaseConnectionHelper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;


@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=DataSubmit Demo Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/myServlet"            
           })
public class FormDataSubmitToSlingServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUid = 1L;
    private static final Logger log = LoggerFactory.getLogger(FormDataSubmitToSlingServlet.class);

    @Reference
  private  DatabaseConnectionHelper help;
    Connection con=null;
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        resp.setContentType("text/plain");
        resp.getWriter().write("Title = " + resource.adaptTo(ValueMap.class).get("jcr:title"));
    }
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
    		throws ServletException, IOException {
    	
 	   log.info("FormDataSubmitToSlingServlet Started ..........");
 	
 	   try {  	
 		   
 		 con= help.getDataBaseConnection("Hello");
 		 
 		 log.info("Sucess ****"+con);
 		   
 		  String firstName = request.getParameter("fname");
          String lastName = request.getParameter("lname");
          String city = request.getParameter("city");   
          
          log.info("firstName ::"+firstName+"lastName ::"+lastName+"city::"+city);
          
        //Encode the submitted form data to JSON
          JSONObject obj=new JSONObject();         
          obj.put("firstname",firstName);
          obj.put("lastname",lastName);
          obj.put("city",city);
          
          //Get the JSON formatted data    
          String jsonData = obj.toJSONString();
          
          log.info("JsonFormatted Data ::"+jsonData); 
             //Return the JSON formatted data
         response.getWriter().write(jsonData);
 					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	
    }
}
