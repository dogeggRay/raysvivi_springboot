package org.spider.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.spider.douban.boxOffice.BoxOfficeNA;

@Mapper
@DS("readDB")
public interface BoxOfficeNADao extends BaseMapper<BoxOfficeNA> {
}
