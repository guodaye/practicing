package com.github.guoyaohui.datasource.router;

import com.github.guoyaohui.datasource.AbstractDynamicDataSourceRouter;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author 郭垚辉
 * @date 2018/09/20
 */
@Slf4j
@Aspect
public class OneDataSourceRouter extends AbstractDynamicDataSourceRouter {

    private static final String DATA_SOURCE_POINT_CUT = "execution(public * com.github.guoyaohui.mapper.one..*.*(..))";

    @PostConstruct
    public void init() {
        super.initDataSource();
    }

    @Pointcut(DATA_SOURCE_POINT_CUT)
    public void switchOneDataSourceCondition() {
    }

    @Before("switchOneDataSourceCondition()")
    public void switchSuitableDataSource(JoinPoint joinPoint) {
        super.prepareExecuteSql(joinPoint);
    }

    @After("switchOneDataSourceCondition()")
    public void swithSuitableDataSourceAfter() {
        super.afterExecuteSql();
    }


}
