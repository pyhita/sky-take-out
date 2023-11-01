package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: kante_yang
 * @Date: 2023/11/1
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断拦截到的是Controller 方法还是其他资源请求
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 1 从请求头中拿到token
        String token = request.getHeader("token");

        // 2 对token进行解析
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long id = (Long) claims.get(JwtClaimsConstant.USER_ID);
            log.info("当前用户的id：{}", id);
            BaseContext.setCurrentId(id);
            return true;
        } catch (Exception e) {
            // 不通过 401 状态码
            response.setStatus(401);
            return false;
        }
    }
}
