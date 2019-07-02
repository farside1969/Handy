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

@Controller
@RequestMapping("job")
public class JobController extends AbstractController {

//displays job index page
    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {

//retrieve user in session
        User user = getUserFromSession(request.getSession());

        model.addAttribute("jobs", jobDao.findByOwner(user));
        model.addAttribute("title", "My Jobs");

        return "job/index";
    }

//displays add job form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("jobClasses", jobClassDao.findAll());
        return "job/add";
    }

//processes add job form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, @RequestParam int jobClassId, Model model,
                                       HttpServletRequest request) {

//if errors returns to add job form
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("jobClasses", jobClassDao.findAll());
            return "job/add";
        }

//retrieve user in session and save as owner with new job
        User owner = getUserFromSession(request.getSession());
        newJob.setOwner(owner);

//retrieve jobClass and save as cat with new job
        JobClass cat = jobClassDao.findOne(jobClassId);
        newJob.setJobClass(cat);

        jobDao.save(newJob);

        return "redirect:";
    }

//display remove job form
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveJobForm(Model model, HttpServletRequest request) {

//retrieve user in session
        User user = getUserFromSession(request.getSession());

        model.addAttribute("jobs", jobDao.findByOwner(user));
        model.addAttribute("title", "Remove Job");
        return "job/remove";
    }

//process remove job form
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveJobForm(@RequestParam int[] ids) {

//delete job from existence via job id and return to job index page
        for (int id : ids) {
/*            Job job = jobDao.findOne(id);
            job.removeContractors();
            jobDao.save(job);                  */
            jobDao.delete(id);
        }

        return "redirect:";
    }

//display jobClass form
    @RequestMapping(value = "jobClass", method = RequestMethod.GET)
    public String displayJobClass(Model model, @RequestParam int uid) {

//ties jobClass with job and returns to job index page
        JobClass cat = jobClassDao.findOne(uid);
        List<Job> jobs = cat.getJobs();
        model.addAttribute("jobs", jobs);
        model.addAttribute("title", "Jobs in Job Class: " + cat.getName());
        return "job/index";
    }
}
