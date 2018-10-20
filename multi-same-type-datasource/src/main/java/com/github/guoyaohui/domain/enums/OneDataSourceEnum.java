package com.github.guoyaohui.domain.enums;

import com.github.guoyaohui.constant.DataSourceConstant.DataSourceOne;
import com.github.guoyaohui.mybatis.handler.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 郭垚辉
 * @date 2018/10/21
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OneDataSourceEnum implements IEnum {
    MASTER(0, DataSourceOne.DATASOURCE_MASTER), SLAVE(1, DataSourceOne.DATASOURCE_SLAVE);
    private int index;
    private String description;
}
