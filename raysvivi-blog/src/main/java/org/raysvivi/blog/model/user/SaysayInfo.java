package org.raysvivi.blog.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.spider.model.BaseDbEntity;

@Data
@TableName(value="t_saysay", autoResultMap = true)
public class SaysayInfo extends BaseDbEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "f_content")
    private String content;

    @TableField(value = "f_context")
    private String context;

    @TableField(value = "f_link")
    private String link;

    @TableField(value = "f_meme")
    private String meme;
}
