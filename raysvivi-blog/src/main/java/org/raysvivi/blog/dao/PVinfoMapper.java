package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.raysvivi.blog.model.FootPrints;

@DS(value = "readDB")
@Mapper
public interface PVinfoMapper  extends BaseMapper<FootPrints> {
    @Update("<script>insert into t_pv (f_module_name,f_relative_id,f_number) values (#{moduleName},#{relaviveId},1) "+
            "ON DUPLICATE KEY UPDATE f_number = f_number+1</script>")
    void numberIncrease(@Param("moduleName") String moduleName,@Param("relaviveId") String relaviveId);


    @Select("SELECT SUM(f_number) FROM `t_pv`")
    Long selectTotalCount();
}
