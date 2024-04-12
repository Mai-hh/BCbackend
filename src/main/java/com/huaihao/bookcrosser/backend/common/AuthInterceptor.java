package com.huaihao.bookcrosser.backend.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 验证 token
        if (token != null && JWTUtil.validateToken(token)) {
            // 从 token 中提取用户 ID
            Long userId = JWTUtil.parseToken(token);
            // 将用户 ID 存储在请求的属性中,以便后续使用
            request.setAttribute("userId", userId);
            return true;
        } else {
            // token 无效,返回 401 Unauthorized 错误
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return false;
        }
    }
}
