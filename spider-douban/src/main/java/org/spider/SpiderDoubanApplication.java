package org.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
public class SpiderDoubanApplication {
    public static void main(String[] args){
        ApplicationContext content = SpringApplication.run(SpiderDoubanApplication.class,args);
//
//        BoxOfficeNAServ bos = content.getBean(BoxOfficeNAServ.class);
//        bos.searchSaveBoxOffice();
    }
}
