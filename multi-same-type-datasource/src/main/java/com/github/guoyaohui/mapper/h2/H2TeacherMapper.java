package com.github.guoyaohui.mapper.h2;

import com.github.guoyaohui.domain.entity.slave.Teacher;
import java.util.List;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
public interface H2TeacherMapper {

    List<Teacher> select();

}
