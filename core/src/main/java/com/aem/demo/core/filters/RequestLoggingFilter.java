/*package com.aem.test.core.filters;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import static org.apache.sling.engine.EngineConstants.*;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component(service = Filter.class, name = "Custom Logging Filter", property = {
		SLING_FILTER_SCOPE + "=" + FILTER_SCOPE_REQUEST, Constants.SERVICE_RANKING + ":Integer=1",
		SLING_FILTER_PATTERN + "=/.*" })



public class RequestLoggingFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class.getName());

	// Called by the web container to indicate to a filter that it is being placed
	// into service.
  String resourcePath;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		log.info("init called");
	}

	// Called by the web container to indicate to a filter that it is being taken
	// out of service.
	
	
	@Override
	public void destroy() {
		log.info("destroyed called");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (!(request instanceof SlingHttpServletRequest) || !(response instanceof SlingHttpServletResponse)) {

			// Not a SlingHttpServletRequest/Response, so ignore.

			chain.doFilter(request, response); // This line would let you proceed to the rest of the filters.

			return;

			}

			final SlingHttpServletResponse slingResponse = (SlingHttpServletResponse) response;

			final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;

			final Resource resource = slingRequest.getResource();

			log.info("*******resource is from ReuestloggingFilter *****" + resource);
			//String resource="/content/trainingproject/Plan/PlanA";
			
			
			
			if (slingRequest.getParameter("resourcePath")!=null) { 
				 resourcePath = slingRequest.getParameter("resourcePath"); 
				 
				 log.info("resourcepath ***********from Logging"+resourcePath);
				 
			 }
			

			//String rootContentPath="/content/trainingproject/Plan/PlanA";
			
			
			 ResourceResolver resourceResolver= slingRequest.getResourceResolver();
		     PageManager pageManager=  resourceResolver.adaptTo(PageManager.class);
		    // Page currentPage=pageManager.getPage("/content/test/testinpage");
		     
		     //log.info("currentPage **********RequestLogging***"+currentPage);
		     
		     
		       
		       // resp.getWriter().write("MyServlet doGET Method Called ***"+currentPage.getPath());

			//if (currentPage.getPath().startsWith("/content/test/Plan/")) {

				// log.info("inside ***********");
			// Is the SlingFilterScope is REQUEST, redirects can be issued.

			// Write your custom code here.

			//slingResponse.sendRedirect("/content/test/demofitrpage.html");
			
			//log.info("after ****************");

			return;

			//}

			// to proceed with the rest of the Filter chain

			//chain.doFilter(request, response);

	}
	}
//}*/