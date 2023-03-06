package org.raysvivi.blog.model;

import lombok.Data;

/**
 * 页面特征数据-通用
 */
@Data
public class CharacteristicData {
    private Integer viewNumber;

    public CharacteristicData (Integer viewNumber){
        this.viewNumber = viewNumber;
    }
}
