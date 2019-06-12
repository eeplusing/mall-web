package com.eplusing.mall.web;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.web.client.RestTemplate;

import com.eplusing.mall.web.bean.Response;
import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class Client  {
    public static void main( String[] args ) throws Exception{
    	
    	CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.1.180:2181", new RetryOneTime(1000));
    	
    	client.start();
    	client.blockUntilConnected();
    	
		ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/soa").build();

		Collection<ServiceInstance<Object>> list = serviceDiscovery.queryForInstances("product");
		
		final List<String> services = new ArrayList<String>();
		
		list.forEach((instance) ->{
			services.add(instance.getAddress() + ":" + instance.getPort());
		});
		
		LoadBalance lb = new LoadBalance(services);
		for(int i = 0; i < 10; i++){
			try {
				RestTemplate rt = new RestTemplate();
				String body = rt.getForObject("http://" + lb.choose() + "/soa/product/1", String.class);
				
				System.out.println(body);
				
				Response resp = new Gson().fromJson(body, Response.class);
				
				System.out.println(resp.getData());
				System.out.println(resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
    	
    }
}
