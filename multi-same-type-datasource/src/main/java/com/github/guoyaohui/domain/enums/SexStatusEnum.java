package com.github.guoyaohui.domain.enums;

import com.github.guoyaohui.mybatis.handler.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SexStatusEnum implements IEnum {
    MAN(0, "男"), WOMAN(1, "女");

    private int index;
    private String description;
}
