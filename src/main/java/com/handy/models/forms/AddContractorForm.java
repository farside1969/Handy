package com.handy.models.forms;

import com.handy.models.Job;
import com.handy.models.Contractor;

import javax.validation.constraints.NotNull;

//TODO correct notes?
public class AddContractorForm {

//field ids
    @NotNull
    private int contractorId;

    @NotNull
    private int jobId;

//field objects
    private Iterable<Job> jobs;

    private Contractor contractor;

//default constructor
    public AddContractorForm() {}

//constructor for jobs and contractor
    public AddContractorForm(Iterable<Job> jobs, Contractor contractor) {
        this.jobs = jobs;
        this.contractor = contractor;
    }

//getters and setters
    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Iterable<Job> getJobs() {
        return jobs;
    }

    public Contractor getContractor() {
        return contractor;
    }
}