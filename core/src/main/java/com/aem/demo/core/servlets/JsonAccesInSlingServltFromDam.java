package com.aem.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.json.simple.JSONObject;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=JsonAccesfrom dam to sling Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/jsonaccsdam" })
public class JsonAccesInSlingServltFromDam extends SlingSafeMethodsServlet {

	private static final long serialVersionUid = 1L;
	private static final Logger log = LoggerFactory.getLogger(JsonAccesInSlingServltFromDam.class);

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		log.info("JsonAccesInSlingServltFromDam Started");

		// Reading the JSON File from DAM.
		Resource original;
		String myJSON = "";

		ResourceResolver resolver = null;
		HashMap<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, "readService"); // readService is my System User.

		try {

			resolver = resolverFactory.getServiceResourceResolver(param);			
			Resource resource = resolver.getResource("/content/dam/test/sample");
			log.info("resource *****" + resource);
			Asset asset = resource.adaptTo(Asset.class);
			original = asset.getOriginal();
			log.info("original *****" + original);
			InputStream content = original.adaptTo(InputStream.class);
			log.info("content *****" + content);
			StringBuilder sb = new StringBuilder();
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));

			while ((line = br.readLine()) != null) {
				sb.append(line);
				
			}
			log.info("json object ::" + sb.toString());
			myJSON = sb.toString();
			JSONObject jsonObj = new JSONObject(); // In jsonObj I will get the data from the JSON file from DAM.
			resp.getWriter().write(myJSON);
		   }

		catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw.toString()));

		}
	}

}
