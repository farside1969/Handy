package com.handy.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

//TODO add notes
@Entity
public class User extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToMany
    @JoinColumn(name = "owner_uid")
    private List<Job> jobs;

    public User() {}

//hash password
    public User(String username, String password) {
        this.username = username;
        this.pwHash = hashPassword(password);
    }

    public String getUsername() {
        return username;
    }

    private static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    protected void addJob(Job job) {
        jobs.add(job);
    }

    public List<Job> getJobs() {
        return jobs;
    }
}