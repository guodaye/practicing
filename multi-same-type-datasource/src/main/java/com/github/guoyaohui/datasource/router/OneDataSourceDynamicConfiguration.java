package com.github.guoyaohui.datasource.router;

import com.github.guoyaohui.common.BeanInitUtil;
import com.github.guoyaohui.constant.DataSourceConstant.DataSourceOne;
import com.github.guoyaohui.datasource.DynamicRoutingDataSource;
import com.github.guoyaohui.domain.enums.OneDataSourceEnum;
import java.util.HashMap;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 郭垚辉
 * @date 2018/10/21
 */
@Slf4j
@Configuration
@MapperScan(
    basePackages = DataSourceOne.BASE_PACKAGES,
    sqlSessionFactoryRef = DataSourceOne.DATASOURCE_SQLSESSION_FACTORY
)
public class OneDataSourceDynamicConfiguration {

    @Autowired
    private BeanFactory beanFactory;

    @Bean(DataSourceOne.DATASOURCE_MASTER)
    public DataSource oneMasterDataSource() {
        return DataSourceBuilder.create()
            .username(DataSourceOne.DATASOURCE_USERNAME)
            .password(DataSourceOne.DATASOURCE_PASSWORD)
            .driverClassName(DataSourceOne.DATASOURCE_DRIVER_NAME)
            .url(DataSourceOne.DATASOURCE_URL_MASTER)
            .build();
    }

    @Bean(DataSourceOne.DATASOURCE_SLAVE)
    public DataSource oneSlaveDataSource() {
        return DataSourceBuilder.create()
            .username(DataSourceOne.DATASOURCE_USERNAME)
            .password(DataSourceOne.DATASOURCE_PASSWORD)
            .driverClassName(DataSourceOne.DATASOURCE_DRIVER_NAME)
            .url(DataSourceOne.DATASOURCE_URL_SLAVE)
            .build();
    }

    @Bean("oneThreadLocal")
    public ThreadLocal<String> oneThreadLocal() {
        return new ThreadLocal<>();
    }

    @Bean
    public OneDataSourceRouter oneDataSourceRouter() {
        OneDataSourceRouter oneDataSourceRouter = new OneDataSourceRouter();
        oneDataSourceRouter.setBeanFactory(beanFactory);
        oneDataSourceRouter.setDataSourceEnum(OneDataSourceEnum.class);
        oneDataSourceRouter.setDataSourceKeyThreadLocal(oneThreadLocal());
        return oneDataSourceRouter;
    }

    @Primary
    @Bean("oneDynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        HashMap<Object, Object> hashMap = new HashMap<>(oneDataSourceRouter().getDatasourceMap());
        dynamicRoutingDataSource.setDefaultTargetDataSource(hashMap.get(OneDataSourceEnum.MASTER.getDescription()));
        dynamicRoutingDataSource.setTargetDataSources(hashMap);
        dynamicRoutingDataSource.setDataSourceKeyThreadLocal(oneThreadLocal());
        return dynamicRoutingDataSource;
    }

    @Primary
    @Bean(DataSourceOne.DATASOURCE_PLATFORM_TRANSACTION_MANAGER)
    public PlatformTransactionManager onePlatformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Primary
    @Bean(DataSourceOne.DATASOURCE_SQLSESSION_FACTORY)
    public SqlSessionFactory oneSqlSessionFactory(BeanFactory beanFactory) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfigLocation(new ClassPathResource(DataSourceOne.DATASOURCE_MYBATIS_CONF));
        bean.setDataSource(dynamicDataSource());
        BeanInitUtil.setPlugins(bean, beanFactory);
        return bean.getObject();
    }
}
