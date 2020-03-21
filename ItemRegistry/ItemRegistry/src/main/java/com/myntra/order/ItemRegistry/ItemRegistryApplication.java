package com.myntra.order.ItemRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = { "com.myntra.order" })
@EnableJpaRepositories(basePackages = {"com.myntra.order"})
@EntityScan(basePackages = "com.myntra.order")
@EnableTransactionManagement
public class ItemRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemRegistryApplication.class, args);
	}

}
