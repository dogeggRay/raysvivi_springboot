package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raysvivi.blog.model.Tag;

import java.util.List;

@DS(value = "readDB")
@Mapper
public interface TagMapper extends BaseMapper<Tag> {


    @Insert("<script>insert into t_tag (f_value,f_module_name,f_relative_id,f_related) values " +
            "<foreach collection='list' item='r' separator=','>"+
            "(#{r.value},#{r.moduleName},#{r.relativeId},#{r.relatived})</foreach></script>")
    void batchInsertTags(@Param("list") List<Tag> actDevs);
}
