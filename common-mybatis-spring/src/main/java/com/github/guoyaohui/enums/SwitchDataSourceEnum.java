package com.github.guoyaohui.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郭垚辉
 * @date 2018/10/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwitchDataSourceEnum {

    /**
     * 数据源的枚举常量的索引值{@link com.github.guoyaohui.mybatis.handler.IEnum#getIndex()}
     */
    int value() default -1;

    /**
     * 数据源的枚举常量的索引值{@link com.github.guoyaohui.mybatis.handler.IEnum#getIndex()}
     */
    int usingIndex() default -1;

    /**
     * 数据源的枚举常量的值{@link com.github.guoyaohui.mybatis.handler.IEnum#getDescription()}
     */
    String usingName() default "";

}
