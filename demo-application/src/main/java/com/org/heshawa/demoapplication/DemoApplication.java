package com.org.heshawa.demoapplication;

import com.org.heshawa.demoapplication.properties.ServiceProperties;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

    @Bean
    @LoadBalanced
    public RestTemplate getRetTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
