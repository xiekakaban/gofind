package com.statestr.core.webcommon;

import com.statestr.core.entity.UserEntity;
import com.statestr.core.util.Constant;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ruantianbo on 2017/3/19.
 */
public class AuthCheckInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod)handler;
        AuthCheck auth= method.getMethodAnnotation(AuthCheck.class);
        //如果没有添加auth annotation 或者 value 为false ，不进行验证
        //只有当value 为true 时进行验证
        if(auth != null && auth.value()){
            UserEntity loginUser = (UserEntity)request.getSession().getAttribute(Constant.USERLOGIN_SESS);
            if(loginUser == null){
                response.sendRedirect("user/login");
                return false;
            }
            return true;
        }
        return true;
    }
}
