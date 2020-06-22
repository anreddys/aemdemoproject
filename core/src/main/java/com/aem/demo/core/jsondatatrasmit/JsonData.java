package com.aem.demo.core.jsondatatrasmit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonData {
	
	/**
	 * Logger
	 */
private static final Logger log = LoggerFactory.getLogger(JsonData.class);
	
JSONObject data;
 
	public JSONObject getJson() {
		//log.info("**getJson called***");
		JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
		try {
			data = (JSONObject)parser.parse(
			      new FileReader("C:/Users/JSON/arrayRetrv.txt"));
			log.info("JSONObject *****"+data);
			//System.out.println("*********"+data);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	
		
	        return data;		
	}
	

	
	/*public static void main(String[] args) {
		JsonData d=new JsonData();
		d.getJson();
	}*/

	
	

}
