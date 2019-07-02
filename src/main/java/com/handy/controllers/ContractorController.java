package com.handy.controllers;

import com.handy.models.Job;
import com.handy.models.Contractor;
import com.handy.models.forms.AddContractorForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "contractor")
public class ContractorController extends AbstractController {

//displays contractor index page
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Contractors");
        model.addAttribute("contractors", contractorDao.findAll());
        return "contractor/index";
    }

//displays add contractor form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddContractorForm(Model model) {
        model.addAttribute("title", "Add Contractor");
        model.addAttribute(new Contractor());
        return "contractor/add";
    }

//processes add contractor form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddContractorForm(Model model, @ModelAttribute @Valid Contractor contractor,
                      Errors errors) {

//if errors return to add contractor page
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Contractor");
            return "contractor/add";
        }

//save contractor with CRUD inheritance and return to view contractor page with added contractor
        contractorDao.save(contractor);

        return "redirect:view/" + contractor.getUid();
    }

//displays view contractor page
    @RequestMapping(value = "view/{contractorId}", method = RequestMethod.GET)
    public String viewContractor(Model model, @PathVariable int contractorId) {

//retrieve contractor via contractor id with CRUD inheritance and return to view contractor page
        Contractor contractor = contractorDao.findOne(contractorId);
        model.addAttribute("contractor", contractor);

        return "contractor/view";
    }

//displays add selected job to contractor form
    @RequestMapping(value = "add-item/{contractorId}", method = RequestMethod.GET)
    public String displayAddContractorJobForm(Model model, @PathVariable int contractorId) {

//retrieve contractor with CRUD inheritance via contractor id
        Contractor contractor = contractorDao.findOne(contractorId);

//create list of jobs for contractor to select from
        AddContractorForm form = new AddContractorForm(
                jobDao.findAll(),
                contractor);

        model.addAttribute("title", "Add contractor: " + contractor.getName());
        model.addAttribute("form", form);
        return "contractor/add-item";
    }

//processes add selected job to contractor form
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String processAddContractorJobForm(Model model,
                          @ModelAttribute @Valid AddContractorForm form, Errors errors) {

//if errors return to add-item contractor page
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "contractor/add-item";
        }

//ties job added to contractor with contractor and returns to view contractor page
        Job theJob = jobDao.findOne(form.getJobId());
        Contractor theContractor = contractorDao.findOne(form.getContractorId());
        theContractor.addItem(theJob);
        contractorDao.save(theContractor);

//displays the specific contractor with their specific job list
        return "redirect:/contractor/view/" + theContractor.getUid();
    }

//TODO future - create ContractorController block to remove Contractor
}
