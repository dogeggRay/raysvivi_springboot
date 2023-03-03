package org.spider.douban.boxOffice;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.spider.SpiderData;
import org.spider.handler.JsonValueHandler;

import java.sql.Timestamp;
import java.util.List;

@Data
@TableName(value="t_box_office_north_american",autoResultMap = true)
public class BoxOfficeNA extends SpiderData {
      @TableField(value = "value" ,typeHandler = JsonValueHandler.class)
      private List<BoxOfficeNABody> value;

      public BoxOfficeNA(String id, Timestamp storageTime){
            this.id = id;
            this.storageTime = storageTime;
      }
      public BoxOfficeNA(){
            super();
      }
}
