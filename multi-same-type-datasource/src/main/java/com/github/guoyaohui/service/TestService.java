package com.github.guoyaohui.service;

import com.github.guoyaohui.domain.entity.master.Student;
import com.github.guoyaohui.domain.entity.slave.Teacher;
import com.github.guoyaohui.mapper.master.StudentMapper;
import com.github.guoyaohui.mapper.slave.TeacherMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
@Component
public class TestService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    public List<Student> getAllStudent() {
        return studentMapper.select();
    }

    public List<Teacher> getAllTeacher() {
        return teacherMapper.select();
    }

}
