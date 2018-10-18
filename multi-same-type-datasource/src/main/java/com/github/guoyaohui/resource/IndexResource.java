package com.github.guoyaohui.resource;

import com.github.guoyaohui.domain.entity.master.Student;
import com.github.guoyaohui.domain.enums.master.SexStatus;
import com.github.guoyaohui.http.JsonResp;
import com.github.guoyaohui.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@RestController
public class IndexResource {

    @Autowired
    private TestService testService;

    @GetMapping("/index")
    public JsonResp index() {
        Student student = testService.selectByIdAndSex(1, SexStatus.MAN);
        return JsonResp.success(student);
    }

}
