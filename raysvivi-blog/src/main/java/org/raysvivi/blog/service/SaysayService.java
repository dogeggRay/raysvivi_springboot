package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.raysvivi.blog.dao.SaysayMapper;
import org.raysvivi.blog.model.user.CommentInfo;
import org.raysvivi.blog.model.user.SaysayInfo;
import org.raysvivi.blog.constant.ModuleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaysayService {
    @Autowired
    private SaysayMapper saysayMapper;

    @Autowired
    private CommentService commentService;

    public List<SaysayInfo> getSaysayList(LocalDateTime dateParam){
        QueryWrapper<SaysayInfo> qw = new QueryWrapper<>();

        LocalDateTime startDateTime = LocalDateTime.of(dateParam.getYear(),01,01,00,00,00);
        LocalDateTime startEndTime = LocalDateTime.of(dateParam.getYear(),12,31,23,59,59);
        qw.ge("create_time",startDateTime);
        qw.le("create_time",startEndTime);

        qw.orderByDesc("create_time");
        List<SaysayInfo> results = saysayMapper.selectList(qw);

        if(CollectionUtils.isEmpty(results)){
            return results;
        }

        List<String> saysayIds = results.stream().map(item -> item.getId()).collect(Collectors.toList());
        List<CommentInfo> commentList = commentService.getConditionList(ModuleEnum.SAY_SAY.getId(),saysayIds);

        if(CollectionUtils.isEmpty(commentList)){
            return results;
        }

        Map<String, List<CommentInfo>> commentMap = commentList.stream().collect(Collectors.groupingBy(CommentInfo::getRelativeId));

        results.forEach((item) ->{
            item.setComments(commentMap.get(item.getId()));
        });
        return results;
    }

    public SaysayInfo getLastSaysay(){
        return saysayMapper.getLastSaysay();
    }
}
