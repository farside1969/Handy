package com.handy.models.data;

import com.handy.models.Job;
import com.handy.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transactional;
import java.util.List;

//TODO remove unused
//CRUD VooDoo
@Transactional
@Repository
public interface JobDao extends CrudRepository<Job, Integer> {

    List<Job> findByOwner(User user);
}
