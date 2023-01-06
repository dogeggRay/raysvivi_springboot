package org.spider.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ShacoFinderServ {

    public void finShaco(){
        try
        {
            Document document = Jsoup.connect("https://movie.douban.com/chart").get();
            System.out.println(document.title());
            Elements links = document.select("a[href]");
            for (Element link : links)
            {
                System.out.println("link : " + link.attr("href"));
                System.out.println("text : " + link.text());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
