package com.aem.demo.core.wcmusepojos;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.demo.core.bean.MultiList;

public class MultiListComponent  extends WCMUsePojo {
	Logger log = LoggerFactory.getLogger(MultiListComponent.class);
	 
	private List multiItems = new ArrayList();

	  @Override
	  public void activate() throws Exception {
		  
		  log.info("Inside Activate ----");

	     Node currentNode = getResource().adaptTo(Node.class);
	     log.info("currentNode----"+currentNode);
	     if(currentNode.hasNode("products")){
	     Node productsNode = currentNode.getNode("products");
	      log.info("productsNode -----"+productsNode);
	     
	     NodeIterator ni =  productsNode.getNodes();
	     log.info("all child nodes -----"+ni);
	     
	     String multitext;
	     String multinum;
	     String multicheck;

	     while (ni.hasNext()) {

	      MultiList multiItem = new MultiList();          
	      Node child = (Node)ni.nextNode();
	      log.info("child ------------"+child);

	         multitext= child.hasProperty("multitext") ? 
	           child.getProperty("multitext").getString(): ""; 
	         multinum = child.hasProperty("multinum") ? 
	           child.getProperty("multinum").getString(): ""; 
	         multicheck = child.hasProperty("multicheck") ? 
	           child.getProperty("multicheck").getString(): "";    
	     
	         multiItem.setMultitext(multitext);
	         multiItem.setMultinum(multinum);
	         multiItem.setMulticheck(multicheck);
	         multiItems.add(multiItem);
	     }  
	    }
	 } 

	 public List getMultiItems() {
		 
		 log.info("multiItems -------------"+multiItems);
	  return multiItems;
	 }
	
	
}
