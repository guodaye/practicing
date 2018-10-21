package com.github.guoyaohui.service;

import com.github.guoyaohui.domain.entity.one.Student;
import com.github.guoyaohui.domain.entity.two.Teacher;
import com.github.guoyaohui.domain.enums.SexStatusEnum;
import com.github.guoyaohui.mapper.one.StudentMapper;
import com.github.guoyaohui.mapper.two.TeacherMapper;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
@Component
@Transactional
public class TestService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    public List<Student> getAllStudent() {
        return studentMapper.select();
    }

    @Transactional
    public List<Student> selectIdList(List<Integer> idList) {
        List<Student> students = studentMapper.selectIdList(idList);
        List<Teacher> select = teacherMapper.select();
        System.out.println(select);
        return students;
    }

    @Transactional
    public Student selectByIdAndName(Integer id, String name) {
        return studentMapper.selectByIdAndName(id, name);
    }

    public Student selectByIdAndSex(Integer id, SexStatusEnum status) {
        return studentMapper.selectByIdAndSex(id, status);
    }

    public Student selectByIdAndTime(Integer id, Date oneDay, Date twoDay, Timestamp timestamp) {
        return studentMapper.selectByIdAndTime(id, oneDay, twoDay, timestamp);
    }

    public int update(Integer id, String name) {
        return studentMapper.update(id, name);
    }

    public List<Teacher> getAllTeacher() {
        return teacherMapper.select();
    }

}
