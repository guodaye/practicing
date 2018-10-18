package com.github.guoyaohui.datasource;

import com.github.guoyaohui.constant.DataSourceConstant.DataSourceOne;
import com.github.guoyaohui.mybatis.interceptor.InterceptorConfiguration;
import com.github.guoyaohui.mybatis.interceptor.ShowFullSqlInterceptor;
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
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
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
    basePackages = DataSourceOne.BASE_PACKAGES,
    sqlSessionFactoryRef = DataSourceOne.DATASOURCE_SQLSESSION_FACTORY
)
public class OneDataSourceConfiguration {

    @Autowired
    InterceptorConfiguration interceptorConfiguration;

    @Primary
    @Bean(DataSourceOne.DATASOURCE)
    public DataSource oneDataSource() {
        return DataSourceBuilder.create()
            .username(DataSourceOne.DATASOURCE_USERNAME)
            .password(DataSourceOne.DATASOURCE_PASSWORD)
            .driverClassName(DataSourceOne.DATASOURCE_DRIVER_NAME)
            .url(DataSourceOne.DATASOURCE_URL)
            .build();
    }

    @Primary
    @Bean(DataSourceOne.DATASOURCE_PLATFORM_TRANSACTION_MANAGER)
    public PlatformTransactionManager onePlatformTransactionManager() {
        return new DataSourceTransactionManager(oneDataSource());
    }

    @Primary
    @Bean(DataSourceOne.DATASOURCE_SQLSESSION_FACTORY)
    public SqlSessionFactory oneSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfigLocation(new ClassPathResource(DataSourceOne.DATASOURCE_MYBATIS_CONF));
        bean.setDataSource(oneDataSource());
        bean.setPlugins(interceptorConfiguration.getInterceptorList().toArray(new Interceptor[0]));
        return bean.getObject();
    }
}