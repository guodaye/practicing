package com.github.guoyaohui.domain.enums;

import com.github.guoyaohui.mybatis.handler.IEnum;
import lombok.Getter;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Getter
public enum SexStatus implements IEnum {
    MAN(0, "男"), WOMAN(1, "女");

    private int index;
    private String description;

    SexStatus(int index, String description) {
        this.index = index;
        this.description = description;
    }
}
