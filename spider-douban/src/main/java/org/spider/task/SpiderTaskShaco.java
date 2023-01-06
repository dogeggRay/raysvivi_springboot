package org.spider.task;

import org.spider.service.BoxOfficeNAServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpiderTaskShaco {
    @Autowired
    private BoxOfficeNAServ boxOfficeNAServ;
    //0 35 2 * * ? 每天2:35执行
    //每周一上午10点15分 0 15 10 ? * MON
    @Scheduled(cron = "0 15 10 ? * MON")
    private void configureTasks() {
        boxOfficeNAServ.searchSaveBoxOffice();
    }

}
