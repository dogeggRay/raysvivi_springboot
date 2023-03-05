package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.raysvivi.blog.model.FootPrints;

@DS(value = "readDB")
@Mapper
public interface PVinfoMapper  extends BaseMapper<FootPrints> {
}
