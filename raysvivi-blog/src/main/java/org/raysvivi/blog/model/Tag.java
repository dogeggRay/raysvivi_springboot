package org.raysvivi.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_tag")
public class Tag {

    @TableField(value = "f_value")
    private String value;

    @TableField(value = "f_parent_tag_value")
    private String parentTagValue;

    @TableField(value = "f_name")
    private String name;

    @TableField(value = "f_module_name")
    private String moduleName;

    @TableField(value = "f_relative_id")
    private String relativeId;

    @TableField(value = "f_related")
    private Boolean relatived;

    public Tag(String moduleName,String relativeId,String value,Boolean relatived){
        this.moduleName = moduleName;
        this.value = value;
        this.relativeId = relativeId;
        this.relatived = relatived;
    }

    public Tag(){}
}
