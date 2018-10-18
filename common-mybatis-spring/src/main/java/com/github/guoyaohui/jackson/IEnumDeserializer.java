package com.github.guoyaohui.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.guoyaohui.common.EnumUtil;
import com.github.guoyaohui.mybatis.handler.IEnum;
import java.io.IOException;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
public class IEnumDeserializer<E extends Enum<E> & IEnum> extends StdDeserializer<E> {

    private Class<E> clazz;

    protected IEnumDeserializer(Class<E> clazz) {
        super(clazz);
        this.clazz = clazz;
    }

    @Override
    public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        E candidate = null;
        if (node.isValueNode()) {
            if (node.isInt()) {
                candidate = EnumUtil.getEnum(clazz, node.asInt());
            } else if (node.isTextual()) {
                candidate = EnumUtil.getEnum(clazz, node.asText());
            }
        } else {
            System.out.println();
        }
        return candidate;
    }

}
