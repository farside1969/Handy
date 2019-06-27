package com.handy.controllers;

import com.handy.models.User;
import com.handy.models.data.JobClassDao;
import com.handy.models.data.JobDao;
import com.handy.models.data.ContractorDao;
import com.handy.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//TODO add notes
public abstract class AbstractController {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected JobDao jobDao;

    @Autowired
    protected JobClassDao jobClassDao;

    @Autowired
    protected ContractorDao contractorDao;

    public static final String userSessionKey = "user_id";

    protected User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findOne(userId);
    }

    protected void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getUid());
    }

    @ModelAttribute("userId")
    public Integer getUserIdFromSession(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute(userSessionKey);
    }
}
