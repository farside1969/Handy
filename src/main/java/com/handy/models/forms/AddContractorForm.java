package com.handy.models.forms;

import com.handy.models.Job;
import com.handy.models.Contractor;

import javax.validation.constraints.NotNull;

//TODO add notes
public class AddContractorForm {

    @NotNull
    private int contractorId;

    @NotNull
    private int jobId;

    private Iterable<Job> jobs;

    private Contractor contractor;

    public AddContractorForm() {}

    public AddContractorForm(Iterable<Job> jobs, Contractor contractor) {
        this.jobs = jobs;
        this.contractor = contractor;
    }

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