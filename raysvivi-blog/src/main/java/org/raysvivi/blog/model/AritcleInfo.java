package org.raysvivi.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_article")
public class AritcleInfo extends BaseDbEntity implements Serializable, Comparable<AritcleInfo> {

    @TableField(value = "f_title")
    private String title;

    @TableField(value = "f_content")
    private String content;

    @TableField(value = "f_abstract")
    private String abstractInfo;

    @TableField(value = "f_module")
    private String module;

    @TableField(value = "f_tag")
    private String tag;

    @TableField(value = "f_image")
    private String image;

    @TableField(value = "f_view_num")
    private int viewNum;

    @Override
    public int compareTo(AritcleInfo o) {
        return 0;
    }
}
