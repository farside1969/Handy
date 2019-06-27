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

//TODO add notes
@Controller
@RequestMapping(value = "contractor")
public class ContractorController extends AbstractController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Contractors");
        model.addAttribute("contractors", contractorDao.findAll());
        return "contractor/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Contractor");
        model.addAttribute(new Contractor());
        return "contractor/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Contractor contractor,
                      Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Contractor");
            return "contractor/add";
        }

        contractorDao.save(contractor);

        return "redirect:view/" + contractor.getUid();
    }

    @RequestMapping(value = "view/{contractorId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int contractorId) {

        Contractor contractor = contractorDao.findOne(contractorId);
        model.addAttribute("contractor", contractor);

        return "contractor/view";
    }

    @RequestMapping(value = "add-item/{contractorId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int contractorId) {

        Contractor contractor = contractorDao.findOne(contractorId);

        AddContractorForm form = new AddContractorForm(
                jobDao.findAll(),
                contractor);

        model.addAttribute("title", "Add contractor: " + contractor.getName());
        model.addAttribute("form", form);
        return "contractor/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model,
                          @ModelAttribute @Valid AddContractorForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "contractor/add-item";
        }

        Job theJob = jobDao.findOne(form.getJobId());
        Contractor theContractor = contractorDao.findOne(form.getContractorId());
        theContractor.addItem(theJob);
        contractorDao.save(theContractor);

        return "redirect:/contractor/view/" + theContractor.getUid();
    }
}
