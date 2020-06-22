/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.aem.demo.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.service.SightlyServiceInterface;

@Model(adaptables=Resource.class)
public class SightlyServiceModel {
	
	
	private static final Logger log = LoggerFactory.getLogger(SightlyServiceModel.class);
	
   /* @Inject
    private SightlyServiceInterface service;
    */
    @OSGiService
    private com.aem.demo.core.service.SightlyServiceInterface services;


   /* @Inject @Named("sling:resourceType") @Default(values="No resourceType")
    protected String resourceType;
*/
    private String message;

    @PostConstruct
    protected void init() {
    	log.info("Called init method");      	     
        message =services.getDeveloperData();     
        log.info("result from OSGI Service ::"+message);
    }

    public String getMessage() {
        return message;
    }
}
