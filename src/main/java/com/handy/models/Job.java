package com.handy.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

//TODO add notes
@Entity
public class Job extends AbstractEntity {

    @ManyToOne
    private User owner;

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private JobClass jobClass;

    @ManyToMany(mappedBy = "jobs")
    private List<Contractor> contractors;

    public Job(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Job() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobClass getJobClass() {
        return jobClass;
    }

    public void setJobClass(JobClass jobClass) {
        this.jobClass = jobClass;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}