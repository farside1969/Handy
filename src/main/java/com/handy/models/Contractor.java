package com.handy.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

//TODO add notes
@Entity
public class Contractor extends AbstractEntity {

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @ManyToMany
    private List<Job> jobs;

    public Contractor() {}

    public void addItem(Job item) {
        jobs.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
