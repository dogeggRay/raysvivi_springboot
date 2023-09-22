package org.raysvivi.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.raysvivi.blog.handler.StrsTypeHandler;
import org.spider.model.BaseDbEntity;

import java.io.Serializable;
import java.util.List;

@Data
@TableName(value="t_article", autoResultMap = true)
public class AritcleInfo extends BaseDbEntity implements Serializable, Comparable<AritcleInfo> {

    @TableField(value = "f_title")
    private String title;

    @TableField(value = "f_content")
    private String content;

    @TableField(value = "f_abstract")
    private String abstractInfo;

    @TableField(value = "f_module")
    private String module;

    @TableField(value = "f_tags",typeHandler = StrsTypeHandler.class)
    private List<String> tags;

    @TableField(value = "f_image")
    private String image;

    @TableField(value = "f_show")
    private Integer show;

    @TableField(value = "f_view_num")
    private int viewNum;

    @TableField(value = "update_time")
    private String updateTime;

    @Override
    public int compareTo(AritcleInfo o) {
        return 0;
    }
}
