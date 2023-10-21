package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.raysvivi.blog.model.user.SaysayInfo;

@DS(value = "readDB")
@Mapper
public interface SaysayMapper extends BaseMapper<SaysayInfo> {

    @Select("select id,f_content content,f_context context,f_link link,f_meme meme,create_time createTime from t_saysay order by id desc limit 1;")
    SaysayInfo getLastSaysay();
}
