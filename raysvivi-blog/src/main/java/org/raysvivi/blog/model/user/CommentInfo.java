package org.raysvivi.blog.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.spider.model.BaseDbEntity;

import java.util.List;

@Data
@TableName("t_comment")
public class CommentInfo extends BaseDbEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "f_module_id")
    private String moduleId;

    @TableField(value = "f_parent_id")
    private String parentId;

    @TableField(value = "f_relative_id")
    private String relativeId;

    @TableField(value = "f_writer_name")
    private String writerName;

    @TableField(value = "f_writer_email")
    private String writerEmail;

    @TableField(value = "f_writer_url")
    private String writerUrl;
    @TableField(value = "f_content")
    private String content;

    @TableField(exist = false)
    private List<CommentInfo> children;
}
