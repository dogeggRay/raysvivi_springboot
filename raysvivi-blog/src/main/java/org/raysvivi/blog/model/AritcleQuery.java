package org.raysvivi.blog.model;

import lombok.Data;
import org.spider.model.page.PageParam;

@Data
public class AritcleQuery extends PageParam {
    private String tag;
}
