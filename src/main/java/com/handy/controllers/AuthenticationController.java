package com.handy.controllers;

import com.handy.models.User;
import com.handy.models.forms.LoginForm;
import com.handy.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//TODO review notes
@Controller
public class AuthenticationController extends AbstractController {

//displays register form
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute(new RegisterForm());
        model.addAttribute("title", "Register");
        return "register";
    }

//processes register form
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {

//if errors returns to register page
        if (errors.hasErrors()) {
            return "register";
        }

        User existingUser = userDao.findByUsername(form.getUsername());

//if username already exists then returns to register page
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "register";
        }

//creates new user, sets in session and returns to home page
        User newUser = new User(form.getUsername(), form.getPassword());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:job";
    }

//displays login form
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(new LoginForm());
        model.addAttribute("title", "Log In");
        return "login";
    }

//processes login form
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid LoginForm form, Errors errors, HttpServletRequest request) {

//if errors returns to login page
        if (errors.hasErrors()) {
            return "login";
        }

        User theUser = userDao.findByUsername(form.getUsername());
        String password = form.getPassword();

//if username does not exist then returns to login page
        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            return "login";
        }

//if password does not exist then returns to login page
        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "login";
        }

//sets user in session and returns to home page
        setUserInSession(request.getSession(), theUser);

        return "redirect:job";
    }

//removes user from session and returns to login page
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
}
