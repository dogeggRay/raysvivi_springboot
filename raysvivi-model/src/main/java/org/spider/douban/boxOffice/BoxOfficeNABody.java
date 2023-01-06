package org.spider.douban.boxOffice;

import lombok.Data;

@Data
public class BoxOfficeNABody {
    private String filmName;

    private String link;

    public BoxOfficeNABody(){
        super();
    }

    public BoxOfficeNABody(String filmName,String link){
        this.filmName = filmName;
        this.link = link;
    }
}
