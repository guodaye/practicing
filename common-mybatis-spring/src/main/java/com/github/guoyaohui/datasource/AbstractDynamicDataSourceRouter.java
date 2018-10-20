package com.github.guoyaohui.datasource;

import com.github.guoyaohui.common.EnumUtil;
import com.github.guoyaohui.enums.SwitchDataSourceEnum;
import com.github.guoyaohui.mybatis.handler.IEnum;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author 郭垚辉
 * @date 2018/09/20
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractDynamicDataSourceRouter {

    /**
     * 全部的数据源集合
     */
    protected Map<String, DataSource> datasourceMap = new HashMap<>();

    /**
     * 工厂类
     */
    protected BeanFactory beanFactory;

    /**
     * 保存当前线程的数据库的名称
     */
    protected ThreadLocal<String> dataSourceKeyThreadLocal;

    /**
     * 数据源的枚举常量
     */
    protected Class<? extends IEnum> dataSourceEnum;


    protected void initDataSource() {
        IEnum[] values = dataSourceEnum.getEnumConstants();
        for (IEnum value : values) {
            String dataSouceName = value.getDescription();
            if (beanFactory.containsBean(dataSouceName)) {
                datasourceMap.put(dataSouceName, (DataSource) beanFactory.getBean(dataSouceName));
            }
        }
        log.info("the project has 【{}】 DataSource, they are 【{}】", datasourceMap.size(), datasourceMap);
    }

    protected void prepareExecuteSql(JoinPoint joinPoint) {
        dataSourceKeyThreadLocal.set(getDataSourceEnum(joinPoint));
    }

    protected void afterExecuteSql() {
        dataSourceKeyThreadLocal.remove();
    }

    /**
     * 当且仅当指定的数据源存在的时候，我们会使用指定的数据元，其他情况，都会直接使用主库的数据源
     *
     * 1. 使用{@link com.github.guoyaohui.enums.SwitchDataSourceEnum#usingName()}
     * 2. 使用{@link com.github.guoyaohui.enums.SwitchDataSourceEnum#value()}
     * 3. 使用{@link com.github.guoyaohui.enums.SwitchDataSourceEnum#usingIndex()}
     * 4. 使用默认的数据源
     */
    protected String getDataSourceEnum(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(SwitchDataSourceEnum.class)) {
            SwitchDataSourceEnum annotation = method.getAnnotation(SwitchDataSourceEnum.class);
            String candidateName = annotation.usingName();
            IEnum iEnum;
            if (StringUtils.isNoneBlank(candidateName) && datasourceMap.containsKey(candidateName)) {
                return candidateName;
            } else if ((iEnum = EnumUtil.getIEnum(dataSourceEnum, annotation.value())) != null) {
                return iEnum.getDescription();
            } else if ((iEnum = EnumUtil.getIEnum(dataSourceEnum, annotation.usingIndex())) != null) {
                return iEnum.getDescription();
            }
        }
        return null;
    }
}
