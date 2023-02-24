package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.raysvivi.blog.model.user.CommentInfo;

@DS(value = "readDB")
@Mapper
public interface CommentMapper extends BaseMapper<CommentInfo> {
    @Update("<script>UPDATE t_comment set delete_flag = 0 WHERE id = #{id}</script>")
    void updateStatus(@Param("id") String id);

}
