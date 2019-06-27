package com.handy.models.data;

import com.handy.models.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transactional;

//TODO remove unused
//CRUD VooDoo
@Repository
@Transactional
public interface ContractorDao extends CrudRepository<Contractor, Integer> {
}
