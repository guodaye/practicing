package com.github.guoyaohui.service;

import com.github.guoyaohui.domain.entity.one.Student;
import com.github.guoyaohui.domain.entity.two.Teacher;
import com.github.guoyaohui.mapper.h2.H2StudentMapper;
import com.github.guoyaohui.mapper.h2.H2TeacherMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
@Component
public class H2TestService {

    @Autowired
    private H2StudentMapper studentMapper;

    @Autowired
    private H2TeacherMapper teacherMapper;

    public List<Student> getAllStudent() {
        return studentMapper.select();
    }

    public List<Teacher> getAllTeacher() {
        return teacherMapper.select();
    }

}
