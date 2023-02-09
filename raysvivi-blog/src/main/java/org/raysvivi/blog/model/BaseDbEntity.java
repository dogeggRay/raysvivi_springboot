package org.raysvivi.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseDbEntity {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "create_time")
    private Timestamp createTime;

    @TableField(value = "update_time")
    private Timestamp updateTime;

    @TableField(value = "delete_flag")
    private int deleteFlag;
}
