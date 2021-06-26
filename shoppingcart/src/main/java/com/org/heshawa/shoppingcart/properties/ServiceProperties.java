package com.org.heshawa.shoppingcart.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:services.properties")
public class ServiceProperties {

    @Value("${service.customer.url}")
    public String customerServiceUrl;

}
