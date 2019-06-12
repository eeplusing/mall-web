package com.eplusing.mall.web;


import org.springframework.web.client.RestTemplate;

import com.eplusing.mall.web.bean.Response;
import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App  {
	public static String BASE_URL = "http://127.0.0.1:8080";
    public static void main( String[] args ) {
    	
    	RestTemplate rt = new RestTemplate();
    	String body = rt.getForObject(BASE_URL + "/soa/product/1", String.class);
    	
    	System.out.println(body);
    	
    	Response resp = new Gson().fromJson(body, Response.class);
    	
    	System.out.println(resp.getData());
        System.out.println(resp);
    }
}
