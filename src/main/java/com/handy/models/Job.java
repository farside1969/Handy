package com.handy.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

//spring tying many User to one owner relationship
    @ManyToOne
    private User owner;

//job name field requirements with spring error message
    @NotNull
    @Size(min=3, max=30, message = "Name must be between 3 and 30 characters")
    private String name;

//job description field requirements with spring error message
    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

//spring tying many JobClass to one jobClass relationship
    @ManyToOne
    private JobClass jobClass;

//spring tying list of many contractors to many jobs relationship
/*    @ManyToMany(mappedBy = "jobs", cascade = { CascadeType.DETACH, CascadeType.REMOVE})
    private List<Contractor> contractors;
*/
//spring tying list of many contractors to many jobs relationship
    @ManyToMany(mappedBy = "jobs")
    private List<Contractor> contractors;

//constructor for name and description
    public Job(String name, String description) {
        this.name = name;
        this.description = description;
    }

//default constructor
    public Job() { }

//getters and setters
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

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public void removeContractors() {
        contractors.clear();
    }
}