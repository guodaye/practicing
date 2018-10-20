package com.github.guoyaohui.datasource;

import com.github.guoyaohui.constant.DataSourceConstant.DataSourceH2;
import com.github.guoyaohui.mybatis.interceptor.InterceptorConfiguration;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 郭垚辉
 * @date 2018/10/20
 */
@Slf4j
@Configuration
@MapperScan(
    basePackages = DataSourceH2.BASE_PACKAGES,
    sqlSessionFactoryRef = DataSourceH2.DATASOURCE_SQLSESSION_FACTORY
)
public class H2DataSourceConfiguration {

    @Autowired
    InterceptorConfiguration interceptorConfiguration;

    @Bean(DataSourceH2.DATASOURCE)
    public DataSource twoDataSource() {
        return DataSourceBuilder.create()
            .username(DataSourceH2.DATASOURCE_USERNAME)
            .password(DataSourceH2.DATASOURCE_PASSWORD)
            .driverClassName(DataSourceH2.DATASOURCE_DRIVER_NAME)
            .url(DataSourceH2.DATASOURCE_URL)
            .build();
    }

    @Bean(DataSourceH2.DATASOURCE_PLATFORM_TRANSACTION_MANAGER)
    public PlatformTransactionManager twoPlatformTransactionManager() {
        DataSource dataSource = twoDataSource();
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(DataSourceH2.DATASOURCE_SQLSESSION_FACTORY)
    public SqlSessionFactory twoSqlSessionFactory() throws Exception {
        DataSource dataSource = twoDataSource();
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfigLocation(new ClassPathResource(DataSourceH2.DATASOURCE_MYBATIS_CONF));
        bean.setPlugins(interceptorConfiguration.getInterceptorList().toArray(new Interceptor[0]));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
}
