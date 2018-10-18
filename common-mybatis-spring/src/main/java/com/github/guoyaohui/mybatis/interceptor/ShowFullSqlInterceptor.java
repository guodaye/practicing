package com.github.guoyaohui.mybatis.interceptor;

import com.github.guoyaohui.mybatis.handler.IEnum;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Component
@Intercepts({
    @Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "queryCursor",
        args = {MappedStatement.class, Object.class, RowBounds.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class ShowFullSqlInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(invocation.getArgs()[1]);
        System.out.println("mybatis 执行的完整的sql是:【" + showFullSql(mappedStatement.getConfiguration(), boundSql) + "】");
        return proceed;
    }

    private String showFullSql(Configuration configuration, BoundSql boundSql) {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();
        String baseSql = boundSql.getSql();

        if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                baseSql = baseSql.replaceFirst("\\?", getRawParam(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String property = parameterMapping.getProperty();
                    if (metaObject.hasGetter(property)) {
                        Object value = metaObject.getValue(property);
                        baseSql = baseSql.replaceFirst("\\?", getRawParam(value));
                    } else if (boundSql.hasAdditionalParameter(property)) {
                        Object additionalParameter = boundSql.getAdditionalParameter(property);
                        baseSql = baseSql.replaceFirst("\\?", getRawParam(additionalParameter));
                    }
                }
            }
        }
        return baseSql;
    }

    private String getRawParam(Object object) {
        if (object instanceof Number) {
            return String.valueOf(object);
        } else if (object instanceof IEnum) {
            return String.valueOf(((IEnum) object).getIndex());
        } else if (object instanceof String) {
            return "'" + object.toString() + "'";
        } else if (object instanceof Date) {
            return "'" + object.toString() + "'";
        } else {
            return null;
        }
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}