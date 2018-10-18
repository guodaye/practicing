package com.github.guoyaohui.datasource;

import com.github.guoyaohui.constant.DataSourceConstant.DataSourceTwo;
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
 * @date 2018/10/17
 */
@Slf4j
@Configuration
@MapperScan(
    basePackages = DataSourceTwo.BASE_PACKAGES,
    sqlSessionFactoryRef = DataSourceTwo.DATASOURCE_SQLSESSION_FACTORY
)
public class TwoDataSourceConfiguration {

    @Autowired
    InterceptorConfiguration interceptorConfiguration;

    @Bean(DataSourceTwo.DATASOURCE)
    public DataSource twoDataSource() {
        return DataSourceBuilder.create()
            .username(DataSourceTwo.DATASOURCE_USERNAME)
            .password(DataSourceTwo.DATASOURCE_PASSWORD)
            .driverClassName(DataSourceTwo.DATASOURCE_DRIVER_NAME)
            .url(DataSourceTwo.DATASOURCE_URL)
            .build();
    }

    @Bean(DataSourceTwo.DATASOURCE_PLATFORM_TRANSACTION_MANAGER)
    public PlatformTransactionManager twoPlatformTransactionManager() {
        DataSource dataSource = twoDataSource();
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(DataSourceTwo.DATASOURCE_SQLSESSION_FACTORY)
    public SqlSessionFactory twoSqlSessionFactory() throws Exception {
        DataSource dataSource = twoDataSource();
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfigLocation(new ClassPathResource(DataSourceTwo.DATASOURCE_MYBATIS_CONF));
        bean.setPlugins(interceptorConfiguration.getInterceptorList().toArray(new Interceptor[0]));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
}