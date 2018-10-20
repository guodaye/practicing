package com.github.guoyaohui.domain.enums;

import com.github.guoyaohui.mybatis.handler.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 生命状态
 *
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LifeStatusEnum implements IEnum {
    ONE(0, "一年级"), TWO(1, "二年级");

    private int index;
    private String description;
}