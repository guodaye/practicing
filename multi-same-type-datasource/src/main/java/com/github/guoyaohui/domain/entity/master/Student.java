package com.github.guoyaohui.domain.entity.master;

import com.github.guoyaohui.domain.enums.master.LifeStatus;
import com.github.guoyaohui.domain.enums.master.SexStatus;
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
    private SexStatus sex;
    private LifeStatus life;
    private Date oneDay;
    private Date twoDay;
    private Timestamp thirdDay;
}

