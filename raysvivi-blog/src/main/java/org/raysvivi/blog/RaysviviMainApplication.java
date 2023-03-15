package org.raysvivi.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = {"org.spider.common.*","org.spider.handler","org.raysvivi.blog.*"})
public class RaysviviMainApplication {
    public static void main(String[] args) {
        ApplicationContext content = SpringApplication.run(RaysviviMainApplication.class,args);
    }
}