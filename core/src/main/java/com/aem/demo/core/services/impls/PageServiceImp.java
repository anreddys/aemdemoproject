package com.aem.demo.core.services.impls;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.service.PageService;
import com.aem.demo.core.service.SightlyServiceInterface;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true, service = PageService.class)
public class PageServiceImp implements PageService {

	private static Logger log = LoggerFactory.getLogger(PageServiceImp.class);
	private String user = "";

	private Session session;

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;

	ResourceResolver resourceResolver = null;

	@Override
	public String CreatePage(String pageName) {
		// TODO Auto-generated method stub

		String pagePath = "/content/test/en";
		String templatePath = "/apps/test/templates/page-home";
		String pageTitle = "demopage";
		Page newPage;
		PageManager pageManager;

		ResourceResolver resolver = null;
		try {

			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			// Invoke the adaptTo method to create a Session
			session = resourceResolver.adaptTo(Session.class);

			// create a page manager instance
			pageManager = resourceResolver.adaptTo(PageManager.class);
			// create a new page
			String renderer = "test/components/homepage";
			newPage = pageManager.create(pagePath, pageName, templatePath, pageTitle);
			if (newPage != null) {
				user = resourceResolver.getUserID();
				Node newNode = newPage.adaptTo(Node.class);
				Node cont = newNode.getNode("jcr:content");
				if (cont != null) {
					//Node rootNode = session.getRootNode();
					//String path = rootNode.getPath();
					Node parNode = JcrUtils.getNodeIfExists(cont, "logo");
					parNode.setProperty("hello", "123");      //read Excel file and Stror in JCR Nodes (Using OSGI Services)
					parNode.setProperty("welcome", "345");     //Excel api and JCR API 
					parNode.setProperty("abc", "987");
					parNode.setProperty("fileReference", "/content/dam/we-retail-screens/we-retail-instore-logo.png");
					Node titlNode = JcrUtils.getNodeIfExists(cont, "title");
					titlNode.setProperty("maintitle", "Mindtree");
					titlNode.setProperty("logo", "Minds");
					titlNode.setProperty("sample title", "Kalingas");

					session.save();

				}
			}

			return pageName;

		} catch (Exception e) {
			// TODO: handle exception
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stackTrace = sw.toString();
			log.error("**error from PageServiceImp**" + sw.toString());
			e.printStackTrace();
		}

		return "";
		// }
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
