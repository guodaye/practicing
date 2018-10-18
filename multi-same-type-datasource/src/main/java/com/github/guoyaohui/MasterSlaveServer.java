package com.github.guoyaohui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MasterSlaveServer {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveServer.class, args);
    }

}
