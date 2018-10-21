package com.github.guoyaohui.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author 郭垚辉
 * @date 2018/10/21
 */
@Aspect
@Component
public class MyIntroducerAspectJ {

    private static final String INTERCEPTOR_CLASS = "com.github.guoyaohui.service..*";

    @DeclareParents(value = INTERCEPTOR_CLASS, defaultImpl = MyIntroducer.class)
    private IMyIntroducer iMyIntroducer;

}
