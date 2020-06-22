/*package com.aem.demo.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.simple.JSONArray;
import org.osgi.service.component.annotations.Reference;

import com.adobe.granite.omnisearch.api.core.OmniSearchService;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

public class SearchComponentServlet extends SlingAllMethodsServlet{

	Map<String, Object> predicateMap;
	
	@Reference
	private OmniSearchService omniSearchService;
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		
		String queryString=request.getParameter("searchQuery");
		String queryPagesLocation=request.getParameter("queryPagesLocation");
		String queryAssetsLocation=request.getParameter("queryAssetsLocation");
		
		JSONArray serachResult=new JSONArray();
		ResourceResolver resourceResolver=request.getResourceResolver();
		Map<String, Object> pagePredicateMap=createPageSearchPredicateMap(queryString,queryPagesLocation);
		serachResult.addAll(parseResult(omniSearchService.getSearchResults(request.getResourceResolver(), pagePredicateMap, 1000, 0),SITE,resouceResolver));
		Map<String, Object> assetPredicateMap=createAssetSearchPredicateMap(queryString,queryAssetsLocation);
		serachResult.addAll(parseResult(omniSearchService.getSearchResults(request.getResourceResolver(), assetPredicateMap, 1000, 0),ASSET,resouceResolver));
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private Map<String, Object> createAssetSearchPredicateMap(String queryString, String queryAssetsLocation) {
		// TODO Auto-generated method stub
		
		
		
		try {
			 predicateMap=new HashMap<>();
				String[] type= {"dam:Asset"};
				String[] fulltext= {queryString};
				String[] contentpath= {queryAssetsLocation};
				
				String[] limit= {"-1"};
				String[] location= {ASSET};
				String[] assetType= {"jcr:content/metadata/dc:format"};
				String[] assetFormats= {"application/jpeg"};
				
				predicateMap.put("miniasset",false);
				predicateMap.put("fulltext", fulltext);
				predicateMap.put(TYPE, type);
				predicateMap.put("path", contentpath);
				predicateMap.put("location", location);	
				predicateMap.put("3_group.property", assetType);	
				predicateMap.put("3_group.p.or", true);	
				predicateMap.put("3_group.property_value", assetFormats);	
				predicateMap.put(P LIMIT, limit);	
				
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return predicateMap;
	}

	private Map<String, Object> createPageSearchPredicateMap(String queryString, String queryPagesLocation) {
		// TODO Auto-generated method stub
		
		try {
			 predicateMap=new HashMap<>();
			String[] type= {"cq:page"};
			String[] fulltext= {queryString};
			String[] contentpath= {queryPagesLocation};
			
			String[] limit= {"-1"};
			String[] location= {};
			
			predicateMap.put("fulltext",fulltext);
			predicateMap.put("type", type);
			predicateMap.put("path", contentpath);
			predicateMap.put("location", location);
			predicateMap.put("p.limit", limit);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return predicateMap;
	}
	
	private JSONArray parseResult(Map<String,SearchResult> queryResult, String location,ResourceResolver resourceResolver) {
		
		JSONArray searchResultArray=new JSONArray();
		SearchResult filteredResult=queryResult.get(location);
		
		if(filteredResult !=null) {
			
			List<Hit> hits=filteredResult.getHits();
			try {
           for(Hit hit:hits) {
        	  String resourcePath= hit.getPath();
        	  String hitPath=resourcePath;
        	  if(resourcePath.contains(JCR CONTENT)) {
        		  resourcePath.substring(0,resourcePath.indexOf(JCR CONTENT));
        	  }
        	  searchResultArray.add(location.equals(SITE)?getPageDetailsFromPath(hitPath,resourceResolver);getAssetDetailsFromPath(hitPath,resourceResolver)))
           }
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		
		
		
		return searchResultArray;
		
		
		
		
	}
	
	
	
}
*/