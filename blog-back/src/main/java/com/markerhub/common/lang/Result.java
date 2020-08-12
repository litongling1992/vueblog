/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Result
 * Author:   Administrator
 * Date:     2020/7/27 17:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.markerhub.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/7/27
 * @since 1.0.0
 */
@Data
public class Result implements Serializable {

    private String code;
    private String msg;
    private Object data;

    public static Result succeess(Object data){
        Result s = new Result();
        s.setCode("200");
        s.setMsg("Succeess");
        s.setData(data);
        return s;
    }

    public static Result failed(String msg){
        Result m = new Result();
        m.setCode("400");
        m.setMsg(msg);
        m.setData(null);
        return m;
    }

    public static Result failed(String msg,Object data){
        Result m = new Result();
        m.setCode("400");
        m.setMsg(msg);
        m.setData(data);
        return m;
    }


}
