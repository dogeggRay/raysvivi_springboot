package org.raysvivi.blog.model.user;

import lombok.Data;

@Data
public class BloggerInfo {

    private Long aritcleNum;

    private Long commentNum;

    private Long viewNum;

    public BloggerInfo(Long aritcleNum,Long commentNum, Long viewNum){
        this.aritcleNum = aritcleNum;
        this.commentNum = commentNum;
        this.viewNum = viewNum;
    }
}
