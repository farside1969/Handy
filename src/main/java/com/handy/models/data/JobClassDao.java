package com.handy.models.data;

import com.handy.models.JobClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//add CRUD inheritance
@Repository
@Transactional
public interface JobClassDao extends CrudRepository<JobClass, Integer> {
}