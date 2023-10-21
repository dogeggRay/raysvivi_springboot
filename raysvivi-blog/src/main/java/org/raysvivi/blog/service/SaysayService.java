package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.raysvivi.blog.dao.SaysayMapper;
import org.raysvivi.blog.model.user.SaysayInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaysayService {
    @Autowired
    private SaysayMapper saysayMapper;

    public List<SaysayInfo> getSaysayList(LocalDateTime dateParam){
        QueryWrapper<SaysayInfo> qw = new QueryWrapper<>();

        LocalDateTime startDateTime = LocalDateTime.of(dateParam.getYear(),01,01,00,00,00);
        LocalDateTime startEndTime = LocalDateTime.of(dateParam.getYear(),12,31,23,59,59);
        qw.ge("create_time",startDateTime);
        qw.le("create_time",startEndTime);
        return saysayMapper.selectList(qw);
    }

    public SaysayInfo getLastSaysay(){
        return saysayMapper.getLastSaysay();
    }
}
