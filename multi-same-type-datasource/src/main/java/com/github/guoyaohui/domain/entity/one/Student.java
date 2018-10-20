package com.github.guoyaohui.domain.entity.one;

import com.github.guoyaohui.domain.enums.LifeStatusEnum;
import com.github.guoyaohui.domain.enums.SexStatusEnum;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 * @author 郭垚辉
 * @date 2018/10/17
 */
@Data
public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private SexStatusEnum sex;
    private LifeStatusEnum life;
    private Date oneDay;
    private Date twoDay;
    private Timestamp thirdDay;
}

