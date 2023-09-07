package com.bcp.yanki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BcpCustomerManagementMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BcpCustomerManagementMicroserviceApplication.class, args);
    }

}
