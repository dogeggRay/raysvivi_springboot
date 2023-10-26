package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.raysvivi.blog.model.user.CommentInfo;

import java.util.List;

@DS(value = "readDB")
@Mapper
public interface CommentMapper extends BaseMapper<CommentInfo> {
    @Update("<script>UPDATE t_comment set delete_flag = 1 WHERE id = #{id}</script>")
    void updateStatus(@Param("id") String id);

    @Select("<script>SELECT id,f_module_id moduleId,f_parent_id parentId,f_relative_id relativeId,f_writer_name writerName,"+
            "f_writer_email writerEmail,f_writer_url writerUrl,f_content content,create_time createTime from t_comment where "+
            "1 = 1 "+
            "<when test='moduleId !=null' > and f_module_id = #{moduleId}</when>"+
            "<when test='relativeIds !=null' > and f_relative_id in "+
                "<foreach collection='relativeIds' item='node' open='(' separator=',' close=')'>"+
                    "#{node}"+
                "</foreach>"+
            "</when>" +
            " order by create_time desc" +
            "</script>")
    List<CommentInfo> getConditionList(@Param("moduleId") String moduleId,@Param("relativeIds") List<String> relativeIds);
}
