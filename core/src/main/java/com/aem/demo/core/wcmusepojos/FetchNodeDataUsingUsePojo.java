package com.aem.demo.core.wcmusepojos;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.demo.core.bean.TestBean;

public class FetchNodeDataUsingUsePojo  extends WCMUsePojo{

	private static final Logger log = LoggerFactory.getLogger(FetchNodeDataUsingUsePojo.class);
	private com.aem.demo.core.bean.TestBean bean;
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		
		
		String text=getProperties().get("text","");
		log.info("text value *******"+text);
		String descripton=getProperties().get("description","");
		log.info("descripton *******"+descripton);
		
		bean=new TestBean();
		bean.setTextVal(text);
		bean.setDescription(descripton);
		//log.info("****"+bean.toString());
				
	}
	
	public TestBean getBean() {
		return this.bean;
		
	}

}
