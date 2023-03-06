package org.raysvivi.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.spider.model.BaseDbEntity;

@Data
@TableName("t_pv")
public class FootPrints {

    @TableField(value = "f_module_name")
    private String moduleName;

    @TableField(value = "f_relative_id")
    private String relativeId;

    @TableField(value = "f_number")
    private Integer number = 1;
}
