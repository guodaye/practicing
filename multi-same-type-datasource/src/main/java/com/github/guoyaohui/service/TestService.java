package com.github.guoyaohui.service;

import com.github.guoyaohui.domain.entity.master.Student;
import com.github.guoyaohui.domain.entity.slave.Teacher;
import com.github.guoyaohui.domain.enums.master.SexStatus;
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

    public Student selectByIdAndName(Integer id, String name) {
        return studentMapper.selectByIdAndName(id, name);
    }
    public Student selectByIdAndSex(Integer id, SexStatus status) {
        return studentMapper.selectByIdAndSex(id, status);
    }

    public Student update(Integer id, String name) {
        return studentMapper.update(id, name);
    }

    public List<Teacher> getAllTeacher() {
        return teacherMapper.select();
    }

}
