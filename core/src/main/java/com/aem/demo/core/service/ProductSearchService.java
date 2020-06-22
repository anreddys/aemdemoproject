package com.aem.demo.core.service;

import org.json.simple.JSONObject;

public interface ProductSearchService {
	
	JSONObject getSearchProducts(String fulltextSearchTerm);

}
