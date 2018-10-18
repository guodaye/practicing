package com.github.guoyaohui.mybatis.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.core.Conventions;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Component
public class InterceptorConfiguration implements BeanDefinitionRegistryPostProcessor {

    private List<String> interceptorBeanDefinitionNameList;

    @Getter
    private List<Interceptor> interceptorList;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        if (ArrayUtils.isNotEmpty(beanDefinitionNames)) {
            Map<BeanDefinition, String> beanDenfinitionMap = new LinkedHashMap<>(beanDefinitionNames.length);
            List<BeanDefinition> beanDefinitionList = new ArrayList<>(beanDefinitionNames.length);
            // 获取所有的mybatis拦截器的BeanDefinition
            for (String beanDefinitionName : beanDefinitionNames) {
                if (StringUtils.isNoneBlank(beanDefinitionName)) {
                    BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
                    if (beanDefinition instanceof AnnotatedBeanDefinition) {
                        AnnotationMetadata metadata = ((AnnotatedBeanDefinition) beanDefinition).getMetadata();
                        String[] interfaceList;
                        if (metadata != null
                            && ArrayUtils.isNotEmpty(interfaceList = metadata.getInterfaceNames())
                            && ArrayUtils.contains(interfaceList, "org.apache.ibatis.plugin.Interceptor")) {
                            beanDenfinitionMap.put(beanDefinition, beanDefinitionName);
                            beanDefinitionList.add(beanDefinition);
                        }
                    }
                }
            }
            if (!CollectionUtils.isEmpty(beanDefinitionList)) {
                interceptorBeanDefinitionNameList = new ArrayList<>(beanDefinitionList.size());
                Collections.sort(beanDefinitionList, new BeanDefinitionOrderComparator());

                for (BeanDefinition beanDefinition : beanDefinitionList) {
                    interceptorBeanDefinitionNameList.add(beanDenfinitionMap.get(beanDefinition));
                }
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (!CollectionUtils.isEmpty(interceptorBeanDefinitionNameList)) {
            interceptorList = new ArrayList<>(interceptorBeanDefinitionNameList.size());
            for (String beanName : interceptorBeanDefinitionNameList) {
                interceptorList.add(beanFactory.getBean(beanName, Interceptor.class));
            }
        }
    }

    public static class BeanDefinitionOrderComparator implements Comparator<BeanDefinition> {

        static final String ORDER_ATTRIBUTE = Conventions.getQualifiedAttributeName(ConfigurationClassPostProcessor.class, "order");

        static int getOrder(BeanDefinition beanDef) {
            Integer order = (Integer) beanDef.getAttribute(ORDER_ATTRIBUTE);
            return (order != null ? order : Ordered.LOWEST_PRECEDENCE);
        }

        @Override
        public int compare(BeanDefinition o1, BeanDefinition o2) {
            int i1 = getOrder(o1);
            int i2 = getOrder(o2);
            return Integer.compare(i1, i2);
        }
    }
}
