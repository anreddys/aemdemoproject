/*
package com.aem.test.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=SlingFilter Demo Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/sample"            
           })
public class MyServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUid = 1L;
    private static final Logger log = LoggerFactory.getLogger(MyServlet.class);
    String resourcePath=null;
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        
        log.info("resource from My MyServlet ******"+resource);
        resp.setContentType("text/plain");
        
         if (req.getParameter("resourcePath")!=null) { 
		 resourcePath = req.getParameter("resourcePath"); 
		 
		 log.info("resourcePath ****from My Servlet ****"+resourcePath);
		 
		
		 
	 }
        
        
     ResourceResolver resourceResolver= req.getResourceResolver();
     PageManager pageManager=  resourceResolver.adaptTo(PageManager.class);
     Page currentPage=pageManager.getPage("/content/test/Plan");
     
     log.info("currentPage **********"+currentPage);
     
     
       
        resp.getWriter().write("MyServlet doGET Method Called ***"+currentPage.getPath());
       // resp.getWriter().write("Title = " + resource.adaptTo(ValueMap.class).get("jcr:title"));
    }
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
    		throws ServletException, IOException {   	
    	
 	   log.info("MyServlet doPost Method Called ***");
 	   
 	   
 	   
 	   
 	   
 	   
 	
    }
}*/