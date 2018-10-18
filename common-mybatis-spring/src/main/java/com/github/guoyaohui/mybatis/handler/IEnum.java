package com.github.guoyaohui.mybatis.handler;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.guoyaohui.jackson.IEnumDeserializer;
import com.github.guoyaohui.jackson.IEnumSerializer;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@JsonSerialize(using = IEnumSerializer.class)
@JsonDeserialize(using = IEnumDeserializer.class)
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
