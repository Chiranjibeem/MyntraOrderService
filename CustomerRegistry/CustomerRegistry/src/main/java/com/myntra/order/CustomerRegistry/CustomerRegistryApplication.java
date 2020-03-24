package com.myntra.order.CustomerRegistry;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.myntra.order"})
@EnableJpaRepositories(basePackages = {"com.myntra.order"})
@EntityScan(basePackages = "com.myntra.order")
@EnableTransactionManagement
public class CustomerRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerRegistryApplication.class, args);
    }


}