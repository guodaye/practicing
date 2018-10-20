package com.github.guoyaohui.constant;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
public class DataSourceConstant {

    /**
     * mysql-数据库1
     */
    public static final class DataSourceOne {

        public static final String BASE_PACKAGES = "com.github.guoyaohui.mapper.one";
        public static final String DATASOURCE_MASTER = "oneMasterDataSource";
        public static final String DATASOURCE_SLAVE = "oneSlaveDataSource";
        public static final String DATASOURCE_PLATFORM_TRANSACTION_MANAGER = "onePlatformTransactionManager";
        public static final String DATASOURCE_SQLSESSION_FACTORY = "ONE_SQL_SESSION_FACTORY";
        public static final String DATASOURCE_MYBATIS_CONF = "com/github/guoyaohui/datasource/one/one-mybatis-config.xml";
        public static final String DATASOURCE_PASSWORD = "123456";
        public static final String DATASOURCE_USERNAME = "root";
        public static final String DATASOURCE_DRIVER_NAME = "com.mysql.jdbc.Driver";
        public static final String DATASOURCE_URL_MASTER = "jdbc:mysql://193.112.66.220:60106/one?useSSL=false&autoReconnect=true&characterEncoding=UTF-8";
        public static final String DATASOURCE_URL_SLAVE = "jdbc:mysql://193.112.66.220:60106/master?useSSL=false&autoReconnect=true&characterEncoding=UTF-8";
    }


    /**
     * mysql-数据库2
     */
    public static final class DataSourceTwo {

        public static final String BASE_PACKAGES = "com.github.guoyaohui.mapper.two";
        public static final String DATASOURCE = "twoDataSource";
        public static final String DATASOURCE_PLATFORM_TRANSACTION_MANAGER = "twoPlatformTransactionManager";
        public static final String DATASOURCE_SQLSESSION_FACTORY = "two_SQL_SESSION_FACTORY";
        public static final String DATASOURCE_MYBATIS_CONF = "com/github/guoyaohui/datasource/two/two-mybatis-config.xml";
        public static final String DATASOURCE_PASSWORD = "123456";
        public static final String DATASOURCE_USERNAME = "root";
        public static final String DATASOURCE_DRIVER_NAME = "com.mysql.jdbc.Driver";
        public static final String DATASOURCE_URL = "jdbc:mysql://193.112.66.220:60106/two?useSSL=false&autoReconnect=true&characterEncoding=UTF-8";
    }

    /**
     * h2-数据库
     */
    public static final class DataSourceH2 {

        public static final String BASE_PACKAGES = "com.github.guoyaohui.mapper.h2";
        public static final String DATASOURCE = "h2DataSource";
        public static final String DATASOURCE_PLATFORM_TRANSACTION_MANAGER = "h2PlatformTransactionManager";
        public static final String DATASOURCE_SQLSESSION_FACTORY = "H2_SQL_SESSION_FACTORY";
        public static final String DATASOURCE_MYBATIS_CONF = "com/github/guoyaohui/datasource/h2/h2-mybatis-config.xml";
        public static final String DATASOURCE_PASSWORD = "123456";
        public static final String DATASOURCE_USERNAME = "sa";
        public static final String DATASOURCE_DRIVER_NAME = "org.h2.Driver";
        // 使用IDEA进行编程时，需要使用以下的配置。对于默认的IDEA配置来说，./ 不是 classpath，而是当前的module的路径
        public static final String DATASOURCE_URL = "jdbc:h2:file:./target/classes/com/github/guoyaohui/datasource/h2/test";
        // 使用ECLIPSE进行编程时或者是打包之后的话，则./的路径，就是classpath
        // public static final String DATASOURCE_URL = "jdbc:h2:file:./com/github/guoyaohui/datasource/h2/test";
    }
}
