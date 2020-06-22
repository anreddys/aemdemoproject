package com.aem.demo.core.services.impls;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
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

import com.aem.demo.core.service.PageService;
import com.aem.demo.core.service.ProductSearchService;
import com.aem.demo.core.service.SightlyServiceInterface;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true, service = ProductSearchService.class)
public class ProductSearchServiceImpl implements ProductSearchService {

	private static Logger log = LoggerFactory.getLogger(ProductSearchServiceImpl.class);

	@Reference
	private com.day.cq.search.QueryBuilder builder;
	@Reference
	private ResourceResolverFactory resolverFactory;
	ResourceResolver resolver = null;
	Session session = null;
	JSONObject resultArray = null;
	JSONObject resultObject = null;

	@Override
	public JSONObject getSearchProducts(String fulltextSearchTerm) {
		// TODO Auto-generated method stub
		
		log.info("getSearchProducts Invoked ****");

		try {

			HashMap<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, "readService"); // readService is my System User.

			resolver = resolverFactory.getServiceResourceResolver(param);
			Resource resource = resolver.adaptTo(Resource.class);
			session = resolver.adaptTo(Session.class);

			fulltextSearchTerm = "Geometrixx";
			// create query description as hash map (simplest way, same as form post)
			Map<String, String> map = new HashMap<String, String>();

			// create query description as hash map (simplest way, same as form post)
			map.put("path", "/content");
			map.put("type", "cq:Page");
			map.put("group.p.or", "true"); // combine this group with OR
			map.put("group.1_fulltext", fulltextSearchTerm);
			map.put("group.1_fulltext.relPath", "jcr:content");
			map.put("group.2_fulltext", fulltextSearchTerm);
			map.put("group.2_fulltext.relPath", "jcr:content/@cq:tags");
			
			log.info("After Query Builder Query Props ****");

			// can be done in map or with Query methods
			map.put("p.offset", "0"); // same as query.setStart(0) below
			map.put("p.limit", "20"); // same as query.setHitsPerPage(20) below

			Query query = builder.createQuery(PredicateGroup.create(map), session);			
			log.info("Query **************"+query);
			
			query.setStart(0);
			query.setHitsPerPage(20);

			SearchResult result = query.getResult();
			
			log.info("SearchResult ****"+result);
			List<Hit> hits = result.getHits();
			
			log.info("hits ***"+hits);

			// paging metadata
			int hitsPerPage = result.getHits().size(); // 20 (set above) or lower
			log.info("hitsPerPage ******"+hitsPerPage);
			long totalMatches = result.getTotalMatches();
			log.info("totalMatches ******"+totalMatches);
			
			long offset = result.getStartIndex();
			log.info("offset ***"+offset);
			long numberOfPages = totalMatches / 20;
			log.info("numberOfPages *****"+numberOfPages);
			JSONObject searchHit;
            
			for (Hit hit : hits) {
         log.info("inside for loop hits......");
				Page page = resource.adaptTo(Page.class);
				String pageTitle = page.getTitle();
				
				log.info("pageTitle ***"+pageTitle);
				
				String pagePath = page.getPath();				
				log.info("pagePath *****"+pagePath);
				
				searchHit = new JSONObject();
				searchHit.put("totalHits", totalMatches);
				searchHit.put("path", pagePath);

				if (pageTitle != null) {
					
					log.info("inside pageTitle condtion ***");
					pageTitle = pagePath.substring(pagePath.lastIndexOf('/') + 1, pagePath.length());

				}

				searchHit.put("title", pageTitle);

				resultArray.put("resultArray", searchHit);
				resultObject.put("data", resultArray);
				// return resultObject;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		log.info("resultObject **********"+resultObject);
		return resultObject;
	}
}
