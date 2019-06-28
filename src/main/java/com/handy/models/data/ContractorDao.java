package com.handy.models.data;

import com.handy.models.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//CRUD VooDoo
@Repository
@Transactional
public interface ContractorDao extends CrudRepository<Contractor, Integer> {
}
