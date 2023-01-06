package org.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication()
@EnableDiscoveryClient
@EnableFeignClients
public class RaysviviMainApplication {
    public static void main(String[] args) {
        ApplicationContext content = SpringApplication.run(RaysviviMainApplication.class,args);
    }
}