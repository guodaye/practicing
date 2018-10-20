package com.github.guoyaohui.resource;

import com.github.guoyaohui.domain.entity.one.Student;
import com.github.guoyaohui.domain.enums.SexStatusEnum;
import com.github.guoyaohui.http.JsonResp;
import com.github.guoyaohui.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@RestController
public class IndexResource implements BeanFactoryAware {

    @Autowired
    private TestService testService;

    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @GetMapping("/index")
    public JsonResp index() {
        Student student = testService.selectByIdAndSex(1, SexStatusEnum.MAN);
        return JsonResp.success(student);
    }

    @PostMapping("/post")
    public JsonResp post(@RequestBody Student student) {
        System.out.println(student);
        student.setSex(SexStatusEnum.WOMAN);
        return JsonResp.success(student);
    }

}
