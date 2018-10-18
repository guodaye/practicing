package com.github.guoyaohui.mapper.master;

import com.github.guoyaohui.domain.entity.master.Student;
import com.github.guoyaohui.domain.enums.master.SexStatus;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
public interface StudentMapper {

    List<Student> select();

    Student selectByIdAndName(@Param("id") Integer id, @Param("name") String name);

    Student selectByIdAndSex(@Param("id") Integer id, @Param("sex") SexStatus sex);

    Student update(@Param("id") Integer id, @Param("name") String name);

}
