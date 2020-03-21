package com.myntra.order.PaymentRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myntra.order"})
@EnableEurekaClient
@EnableJpaRepositories(basePackages = {"com.myntra.order"})
@EntityScan(basePackages = "com.myntra.order")
@EnableTransactionManagement
public class PaymentRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentRegistryApplication.class, args);
    }

}
