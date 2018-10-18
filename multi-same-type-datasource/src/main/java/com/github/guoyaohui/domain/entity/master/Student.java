package com.github.guoyaohui.domain.entity.master;

import com.github.guoyaohui.domain.enums.master.LifeStatus;
import com.github.guoyaohui.domain.enums.master.SexStatus;
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
}
