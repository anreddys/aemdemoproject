package com.aem.demo.core.services.impls;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.service.SearchServiceInterface;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
@Component(immediate=true,service=SearchServiceInterface.class)
public class SearchServiceImpl implements SearchServiceInterface {

	private static Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Reference
	private PageManager pageManager;

	ResourceResolver resourceResolver;
	String strs;

	@Override
	public String getResult(String searchText) {
		// TODO Auto-generated method stub
		
		log.info("inside getResult");

		/*try {

			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, "readService");
			ResourceResolver resourceResolver = null;
			resourceResolver = resolverFactory.getServiceResourceResolver(param);
			Session session = resourceResolver.adaptTo(Session.class);
			log.info("session ****"+session);

			javax.jcr.query.QueryManager queryManager = session.getWorkspace().getQueryManager();
	  String sqlStatement = "select * from cq:Page where jcr:path like '/content/aemdemo/homepage%' and contains(*,'"+ searchText + "') order by jcr:score desc ";
			//String sqlStatement = "SELECT * FROM [cq:PageContent] WHERE CONTAINS(author, 'Sunil')";
			javax.jcr.query.Query query = queryManager.createQuery(sqlStatement, "JCR-SQL2");
			log.info("Query *****Query****"+query);
			javax.jcr.query.QueryResult result = query.execute();
			log.info("Result *****Result"+result);
			//javax.jcr.NodeIterator nodeIter = result.getNodes();

			
			 * while ( nodeIter.hasNext() ) { log.info("From the search"); javax.jcr.Node
			 * node = nodeIter.nextNode();
			 * 
			 * 
			 * String title = node.getProperty("jcr:title").getString(); author =
			 * node.getProperty("author").getString(); }
			 

			if (result.getNodes() != null && result.getNodes().hasNext()) {
				
				log.info("Inside if condtion...");

				NodeIterator it = result.getNodes();
				while (it.hasNext()) {

					Node node = it.nextNode();

					String npath = node.getPath();
					log.info("npath ********"+npath);

					Page contentPage = pageManager.getContainingPage(resourceResolver.getResource(npath));
					String title = contentPage.getTitle();
					String path = contentPage.getPath() + ".html";
					log.info("title ***"+title);
					log.info("path ******"+path);
					 strs="String found..";

				}
				
			}else {
			
				  strs="String not found";
				 log.info("Please Try Again ...No Results found ...");
				
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
*/		return "hello";
	}

}
