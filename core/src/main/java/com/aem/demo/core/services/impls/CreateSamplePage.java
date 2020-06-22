package com.aem.demo.core.services.impls;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true,service=CreateSamplePage.class)

public class CreateSamplePage   {

	private static Logger log = LoggerFactory.getLogger(CreateSamplePage.class);	
@Reference
private ResourceResolverFactory resolverFactory;
private ResourceResolver resourceResolver; 
private Session session;
private void createPage() throws Exception {
	
 String path="/content/test";
 String pageName="samplePage";
 String pageTitle="Sample Page";
 String template="/apps/test/templates/test-page";
 String renderer="test/components/homepage";

 
 
 
 
    try { 
    	
    	resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());      
        //Invoke the adaptTo method to create a Session
		log.info("****resourceResolver******"+resourceResolver);
        session = resourceResolver.adaptTo(Session.class);	
        log.info("*********************SEE"+session); 	
    	
    if (session != null) {
 
    // Create Page      
    PageManager pageManager = this.resourceResolver.adaptTo(PageManager.class);
    Page prodPage = pageManager.create(path, pageName, template, pageTitle);
    Node pageNode = prodPage.adaptTo(Node.class);
 
 Node jcrNode = null;
 if (prodPage.hasContent()) { 
    jcrNode = prodPage.getContentResource().adaptTo(Node.class);
 } else {                   
       jcrNode = pageNode.addNode("jcr:content", "cq:PageContent");
       } 
           jcrNode.setProperty("sling:resourceType", renderer);     
           Node parNode = jcrNode.addNode("par");
           parNode.setProperty("sling:resourceType", "foundation/components/parsys"); 
        Node textNode = parNode.addNode("text");
      textNode.setProperty("sling:resourceType", "foundation/components/text");
      textNode.setProperty("textIsRich", "true");
      textNode.setProperty("text", "Test page"); 
 
 session.save();
        session.refresh(true);
  }
           
 } catch (Exception e) {
 throw e; 
 }  
   } 


private Map<String,Object> getSubServiceMap(){
	log.info("*****Inside getSubservice method ****");
		Map<String,Object>  serviceMap=null;
	
	try {			
		
		serviceMap=new HashMap<String,Object>();
		serviceMap.put(ResourceResolverFactory.SUBSERVICE, "readService");
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		StringWriter errors=new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
	}
log.info("*****getSubservice Method End*****");	
	return serviceMap;
	
}
}

