package com.handy.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Contractor extends AbstractEntity {

//contractor name field requirements with spring error message
    @NotNull
    @Size(min=3, max=30, message = "Name must be between 3 and 30 characters")
    private String name;

//spring tying list of many Job to many jobs relationship
    @ManyToMany
    private List<Job> jobs;

//default constructor
    public Contractor() {}

//add item to jobs
    public void addItem(Job item) {
        jobs.add(item);
    }

//getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//list of jobs
    public List<Job> getJobs() {
        return jobs;
    }
}
