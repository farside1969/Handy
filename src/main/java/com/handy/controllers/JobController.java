package com.handy.controllers;

import com.handy.models.Job;
import com.handy.models.JobClass;
import com.handy.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

//TODO add notes
@Controller
@RequestMapping("job")
public class JobController extends AbstractController {

//request path: /job
    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {

        User user = getUserFromSession(request.getSession());

        model.addAttribute("jobs", jobDao.findByOwner(user));
        model.addAttribute("title", "My Jobs");

        return "job/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("jobClasses", jobClassDao.findAll());
        return "job/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, @RequestParam int jobClassId, Model model,
                                       HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("jobClasses", jobClassDao.findAll());
            return "job/add";
        }

        User owner = getUserFromSession(request.getSession());
        newJob.setOwner(owner);

        JobClass cat = jobClassDao.findOne(jobClassId);
        newJob.setJobClass(cat);

        jobDao.save(newJob);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveJobForm(Model model, HttpServletRequest request) {

        User user = getUserFromSession(request.getSession());

        model.addAttribute("jobs", jobDao.findByOwner(user));
        model.addAttribute("title", "Remove Job");
        return "job/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveJobForm(@RequestParam int[] ids) {

        for (int id : ids) {
            jobDao.delete(id);
        }

        return "redirect:";
    }

    @RequestMapping(value = "jobClass", method = RequestMethod.GET)
    public String jobClass(Model model, @RequestParam int uid) {

        JobClass cat = jobClassDao.findOne(uid);
        List<Job> jobs = cat.getJobs();
        model.addAttribute("jobs", jobs);
        model.addAttribute("title", "Jobs in JobClass: " + cat.getName());
        return "job/index";
    }
}
