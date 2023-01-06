package org.spider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.spider.common.util.DateTimeUtil;
import org.spider.dao.BoxOfficeNADao;
import org.spider.douban.boxOffice.BoxOfficeNA;
import org.spider.douban.boxOffice.BoxOfficeNABody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoxOfficeNAServ {
    Logger logger = Logger.getLogger(BoxOfficeNAServ.class);

    @Autowired
    private BoxOfficeNADao bonaDao;
    public void searchSaveBoxOffice() {
        try{
            Document document = Jsoup.connect("https://movie.douban.com/chart").get();
            //北美票房
            Element naTop = document.select("h2:contains(北美票房榜)").parents().get(0);

            JXDocument jxDocument = new JXDocument(naTop.children());
            List<JXNode> nodes = jxDocument.selN("//a[@href]");


            BoxOfficeNA na = new BoxOfficeNA(DateTimeUtil.getCurrentDate(),new Timestamp(System.currentTimeMillis()));
            List<BoxOfficeNABody> values = new ArrayList<>();
            for(JXNode node : nodes){
                Element nodeEle = (Element)node.value();
                BoxOfficeNABody body = new BoxOfficeNABody(nodeEle.text(),nodeEle.attr("href"));
                values.add(body);
            }
            na.setValue(values);

            bonaDao.insert(na);
        }catch(IOException e){
            logger.error("searchSaveBoxOffice failed,message:"+e.getMessage());
        }

    }

    public IPage<BoxOfficeNA> getAllBO(){
        IPage<BoxOfficeNA> result = bonaDao.selectPage(new Page<BoxOfficeNA>(1, 10),null);
        return result;
    }
}
