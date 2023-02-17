package org.spider.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.spider.model.BaseDbEntity;

import java.io.Serializable;

@Data
@TableName("t_user_info")
public class UserInfo extends BaseDbEntity implements Serializable {

    @TableField(value = "name")
    private String name;

    @TableField(value = "pwd")
    private String pwd;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "image")
    private String image;
}
