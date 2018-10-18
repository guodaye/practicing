package com.github.guoyaohui.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.guoyaohui.mybatis.handler.IEnum;
import java.io.IOException;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
public class IEnumSerializer<E extends Enum<E> & IEnum> extends StdSerializer<E> {

    protected IEnumSerializer() {
        this(null);
    }

    public IEnumSerializer(Class<E> t) {
        super(t);
    }

    @Override
    public void serialize(E value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("index");
        gen.writeNumber(value.getIndex());
        gen.writeFieldName("name");
        gen.writeString(value.getDescription());
        gen.writeEndObject();
    }

}
