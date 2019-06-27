package com.handy.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

//TODO add notes
@Entity
public class JobClass extends AbstractEntity {

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @OneToMany
    private List<Job> jobs = new ArrayList<>();

    public JobClass() {}

    public JobClass(String name) {
        this.name = name;
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
