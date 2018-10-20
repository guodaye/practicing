package com.github.guoyaohui.common;

import com.github.guoyaohui.mybatis.interceptor.InterceptorConfiguration;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author 郭垚辉
 * @date 2018/10/21
 */
public class BeanInitUtil {

    public static void setPlugins(SqlSessionFactoryBean bean, BeanFactory beanFactory) {
        if (beanFactory.containsBean("interceptorConfiguration")) {
            InterceptorConfiguration configuration = beanFactory.getBean(InterceptorConfiguration.class);
            if (!CollectionUtils.isEmpty(configuration.getInterceptorList())) {
                bean.setPlugins(configuration.getInterceptorList().toArray(new Interceptor[0]));
            }
        }
    }

}
