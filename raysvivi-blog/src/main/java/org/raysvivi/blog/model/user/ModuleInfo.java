package org.raysvivi.blog.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_module")
public class ModuleInfo {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "f_name")
    private String name;

    @TableField(value = "f_value")
    private String value;
}
