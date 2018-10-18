package com.github.guoyaohui.service;


import com.github.guoyaohui.MasterSlaveServer;
import com.github.guoyaohui.domain.entity.master.Student;
import com.github.guoyaohui.domain.enums.master.SexStatus;
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
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void getAllStudent() {
        Student student = testService.selectByIdAndSex(1, SexStatus.MAN);
        List<Student> allStudent = testService.getAllStudent();
        Student master = testService.selectByIdAndName(1, "master");
        int llll = testService.update(1, "llll");
    }
}