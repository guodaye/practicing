package com.github.guoyaohui.mapper.h2;

import com.github.guoyaohui.domain.entity.one.Student;
import com.github.guoyaohui.domain.enums.SexStatusEnum;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
public interface H2StudentMapper {

    List<Student> select();

    List<Student> selectIdList(@Param("idList") List<Integer> idList);

    Student selectByIdAndName(@Param("id") Integer id, @Param("name") String name);

    Student selectByIdAndSex(@Param("id") Integer id, @Param("sex") SexStatusEnum sex);

    Student selectByIdAndTime(@Param("id") Integer id, @Param("oneDay") Date oneDay, @Param("twoDay") Date twoDay, @Param("thirdDay") Date thirdDay);

    int update(@Param("id") Integer id, @Param("name") String name);

}
