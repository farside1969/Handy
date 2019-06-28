package com.handy.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User extends AbstractEntity {

//user username field requirements with spring error message
    @NotNull
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

//spring encryption of password
    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//spring tying one owner to list of many Job of jobs
    @OneToMany
    @JoinColumn(name = "owner_uid")
    private List<Job> jobs;

//default constructor
    public User() {}

//hash password constructor
    public User(String username, String password) {
        this.username = username;
        this.pwHash = hashPassword(password);
    }

//getter
    public String getUsername() {
        return username;
    }

//hash password and verify
    private static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

//add job to jobs
    protected void addJob(Job job) {
        jobs.add(job);
    }

//list of jobs
    public List<Job> getJobs() {
        return jobs;
    }
}