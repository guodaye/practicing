package com.github.guoyaohui;

import com.github.guoyaohui.annotation.EnableMybatisInteceptor;
import com.github.guoyaohui.mybatis.interceptor.ShowFullSqlInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
@EnableMybatisInteceptor
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Import(ShowFullSqlInterceptor.class)
public class MasterSlaveServer {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveServer.class, args);
    }

}
