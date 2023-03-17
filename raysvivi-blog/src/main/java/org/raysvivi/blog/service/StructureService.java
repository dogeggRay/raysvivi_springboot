package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.raysvivi.blog.dao.StructureMapper;
import org.raysvivi.blog.model.structure.StructureInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StructureService {

    @Autowired
    private StructureMapper structureMapper;

    public StructureInfo queryOne(String name){
        return structureMapper.selectOne(new QueryWrapper<StructureInfo>().eq("f_name",name));
    }

    public void addOne(StructureInfo structureInfo){
        structureMapper.insert(structureInfo);
    }

    public void updateOne(StructureInfo structureInfo){
        structureMapper.updateById(structureInfo);
    }

    public List<String> queryNameList(){
        List<StructureInfo> infosInDB = structureMapper.selectList(new QueryWrapper<StructureInfo>());
        if(CollectionUtils.isEmpty(infosInDB)){
            return null;
        }
        return infosInDB.stream().map(StructureInfo::getName).collect(Collectors.toList());
    }
}
