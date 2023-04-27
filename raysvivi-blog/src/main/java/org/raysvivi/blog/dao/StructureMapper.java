package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.raysvivi.blog.model.structure.StructureInfo;
import org.spider.model.CommonNode;

import java.util.List;

@DS(value = "readDB")
@Mapper
public interface StructureMapper extends BaseMapper<StructureInfo> {

    @Select("select `id` id,`f_name` label from  t_structure where delete_flag = 0")
    List<CommonNode> getAllList();
}
