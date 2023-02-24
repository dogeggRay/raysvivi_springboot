package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.raysvivi.blog.model.user.CommentInfo;

@DS(value = "readDB")
@Mapper
public interface CommentMapper extends BaseMapper<CommentInfo> {
}
