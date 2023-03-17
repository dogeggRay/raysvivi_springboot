package org.raysvivi.blog.model.structure;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.raysvivi.blog.handler.JsonObjTypeHandler;
import org.spider.model.BaseDbEntity;

import java.util.List;

@Data
@TableName(value="t_structure", autoResultMap = true)
public class StructureInfo extends BaseDbEntity {

    @JsonProperty("name")
    @TableField("f_name")
    private String name;

    @JsonProperty("value")
    @TableField(value="f_value",typeHandler = JsonObjTypeHandler.class)
    private StructureValue value;
}
