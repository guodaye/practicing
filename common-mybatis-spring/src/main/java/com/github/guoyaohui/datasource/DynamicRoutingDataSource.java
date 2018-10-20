package com.github.guoyaohui.datasource;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态切换数据源
 *
 * @author 郭垚辉
 * @date 2018/09/20
 */
@Slf4j
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 保存当前线程的数据库的名称
     */
    private ThreadLocal<String> dataSourceKeyThreadLocal;

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKeyThreadLocal.get();
    }
}
