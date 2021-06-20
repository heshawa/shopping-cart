package com.org.heshawa.payment.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:services.properties")
public class ServiceProperties {

    @Value("${service.shopping.cart.url}")
    public String shoppingCartServiceUrl;

    @Value("${service.customer.url}")
    public String customerServiceUrl;

    @Value("${service.payment.url}")
    public String paymentServiceUrl;


}
