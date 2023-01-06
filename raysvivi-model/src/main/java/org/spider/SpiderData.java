package org.spider;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SpiderData {

    @TableField("id")
    protected String id;

    @TableField("storage_time")
    protected Timestamp storageTime;

    @TableField("delete_flag")
    protected int deleteFlag;
}
