package com.handy.controllers;

import com.handy.models.JobClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

//TODO add notes
@Controller
@RequestMapping("jobClass")
public class JobClassController extends AbstractController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "JobClasses");
        model.addAttribute("jobClasses", jobClassDao.findAll());
        return "jobClass/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobClass());
        model.addAttribute("title", "Add JobClass");
        return "jobClass/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid JobClass jobClass, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add JobClass");
            return "jobClass/add";
        }

        jobClassDao.save(jobClass);
        return "redirect:";
    }
}
