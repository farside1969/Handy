package com.handy;

import com.handy.controllers.AbstractController;
import com.handy.models.User;
import com.handy.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//available pages prior to login
        List<String> nonAuthPages = Arrays.asList("/", "/login", "/register");

//required sign-in for authorized page access
        if ( !nonAuthPages.contains(request.getRequestURI()) ) {

//if not logged in create a session
            boolean isLoggedIn = false;
            User user;
            Integer userId = (Integer) request.getSession().getAttribute(AbstractController.userSessionKey);

//if userId exists then go get it
            if (userId != null) {
                user = userDao.findOne(userId);

//if user logged in session then isLoggedIn is true
                if (user != null)
                    isLoggedIn = true;
            }

//if user not logged in redirect to login page
            if (!isLoggedIn) {
                response.sendRedirect("/login");
                return false;
            }
        }

//passed all conditions and therefore returns true
        return true;
    }
}
