package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.spider.model.user.UserInfo;

@DS(value = "readDB")
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
