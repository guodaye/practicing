package com.github.guoyaohui.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResp {

    private int status;
    private String errorMsg;
    private Object data;


    public static JsonResp failed(String errorMsg) {
        return new JsonResp(-1, errorMsg, null);
    }

    public static JsonResp success(Object object) {
        return new JsonResp(0, null, object);
    }

}
