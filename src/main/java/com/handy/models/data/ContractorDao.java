package com.handy.models.data;

import com.handy.models.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//add CRUD inheritance
@Repository
@Transactional
public interface ContractorDao extends CrudRepository<Contractor, Integer> {
}
