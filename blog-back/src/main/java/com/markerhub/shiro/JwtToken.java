/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JwtToken
 * Author:   Administrator
 * Date:     2020/7/28 9:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.markerhub.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/7/28
 * @since 1.0.0
 */
public class JwtToken implements AuthenticationToken {

    public String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
