package com.github.guoyaohui.domain.enums;

import com.github.guoyaohui.mybatis.handler.IEnum;
import lombok.Getter;

/**
 * 生命状态
 *
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Getter
public enum LifeStatus implements IEnum {
    ONE(0, "一年级"), TWO(1,"二年级");

    private int index;
    private String description;

    LifeStatus(int index, String description) {
        this.index = index;
        this.description = description;
    }
}