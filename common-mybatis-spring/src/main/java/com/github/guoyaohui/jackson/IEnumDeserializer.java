package com.github.guoyaohui.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.guoyaohui.common.EnumUtil;
import com.github.guoyaohui.mybatis.handler.IEnum;
import java.io.IOException;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
public class IEnumDeserializer<E extends Enum<E> & IEnum> extends StdDeserializer<E> implements ContextualDeserializer {

    private Class<E> clazz;

    public IEnumDeserializer() {
        this(null);
    }

    public IEnumDeserializer(Class<E> t) {
        super(t);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        this.clazz = (Class<E>) ctxt.getContextualType().getRawClass();
        return this;
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
            JsonNode indexJsonNode = node.get("index");
            JsonNode nameJsonNode = node.get("name");
            if (indexJsonNode != null && nameJsonNode != null) {
                candidate = EnumUtil.getEnum(clazz, indexJsonNode.asInt(), nameJsonNode.asText());
                if (candidate == null) {
                    throw new RuntimeException("index : " + indexJsonNode.asInt() + ",name : " + nameJsonNode.asText() + ",但是没有这种匹配的枚举常量存在");
                }
            } else if (indexJsonNode != null) {
                candidate = EnumUtil.getEnum(clazz, indexJsonNode.asInt());
            } else if (nameJsonNode != null) {
                candidate = EnumUtil.getEnum(clazz, nameJsonNode.asText());
            }
        }
        return candidate;
    }

}
