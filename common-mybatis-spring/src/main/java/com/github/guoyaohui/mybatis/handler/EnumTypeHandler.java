package com.github.guoyaohui.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Data
public class EnumTypeHandler<E extends Enum<E> & IEnum> extends BaseTypeHandler<E> {

    private Class<E> clazz;
    private Map<Integer, E> enumMap = new HashMap<>();

    public EnumTypeHandler(Class<E> clazz) {
        this.clazz = clazz;
        E[] constants = clazz.getEnumConstants();
        for (E e : constants) {
            enumMap.put(e.getIndex(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getIndex());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return enumMap.get(rs.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return enumMap.get(rs.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return enumMap.get(cs.getInt(columnIndex));
    }
}
