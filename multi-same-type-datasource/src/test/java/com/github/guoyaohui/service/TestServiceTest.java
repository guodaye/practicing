package com.github.guoyaohui.service;


import com.github.guoyaohui.MasterSlaveServer;
import com.github.guoyaohui.domain.entity.one.Student;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Student> students = testService.selectIdList(integers);
        System.out.println(students);
    }

    @Test
    public void update() {
        int update = testService.update(1, UUID.randomUUID().toString());
        System.out.println(update);
    }
}