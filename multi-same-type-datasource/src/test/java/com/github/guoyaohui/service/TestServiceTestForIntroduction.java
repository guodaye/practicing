package com.github.guoyaohui.service;


import com.github.guoyaohui.MasterSlaveServer;
import com.github.guoyaohui.domain.entity.one.Student;
import com.github.guoyaohui.introduction.IMyIntroducer;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    classes = MasterSlaveServer.class
)
public class TestServiceTestForIntroduction {

    @Autowired
    private TestService testService;

    @Test
    public void getAllStudent() {
        List<Student> students = testService.selectIdList(Arrays.asList(1, 2, 3));
        System.out.println(students);

        // 如果使用DeclareParents这个注解可以为某些类添加方法
        IMyIntroducer testService = (IMyIntroducer) this.testService;
        testService.testDoSomething();
    }
}