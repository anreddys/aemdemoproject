//package com.aem.demo.core.models;
//
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.Via;
//import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
//import org.apache.sling.models.annotations.injectorspecific.Self ;
//
//import com.adobe.cq.wcm.core.components.models.Title;
//import com.day.cq.wcm.api.Page;
//import com.day.cq.wcm.foundation.Table; 
// 
//@Model(adaptables = SlingHttpServletRequest.class, adapters = Table.class, resourceType = "aemdemo/components/content/title")
//public class PageHeadline implements Title {
// 
//    @ScriptVariable
//    private Page currentPage;
// 
//    @Self @Via(type = ResourceSuperType.class, value = "")
//    private Title title;
// 
//    @Override
//    public String getText() {
//        return "My AEM Project - " + title ; 
//    }
// 
//    @Override
//    public String getType() {
//        return title.getType();
//    }
//}