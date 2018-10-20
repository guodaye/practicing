package com.github.guoyaohui.service;

import com.github.guoyaohui.MasterSlaveServer;
import com.github.guoyaohui.domain.entity.one.Student;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 郭垚辉
 * @date 2018/10/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    classes = MasterSlaveServer.class
)
public class H2TestServiceTest {

    @Autowired
    private H2TestService testService;

    @Test
    public void getAllStudent() {
        List<Student> students = testService.getAllStudent();
        System.out.println(students);
//        Date date = new Date();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Student student = testService.selectByIdAndTime(1, date, date, timestamp);
//        System.out.println(student);
    }
}