package com.github.guoyaohui.mybatis.handler;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.guoyaohui.jackson.EnumDeserializer;
import com.github.guoyaohui.jackson.EnumSerializer;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@JsonSerialize(using = EnumSerializer.class)
@JsonDeserialize(using = EnumDeserializer.class)
public interface IEnum {

    /**
     * 数据库中的数值
     */
    int getIndex();

    /**
     * 对于该值的描述
     */
    String getDescription();

}
