package com.eplusing.mall.web;

import java.util.List;

public class LoadBalance {
	private int index = 0;
	private List<String> services;
	
	public LoadBalance(List<String> services){
		this.services = services;
	}
	
	public String choose(){
		String service = services.get(index);
		index++;
		
		if(index >= services.size()){
			index = 0;
		}
		
		System.out.println(service);
		
		return service;
	}
	
}
