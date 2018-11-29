package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.Bill;
import javax.transaction.Transactional;

import com.moommim.moommim_web.model.UserAccount;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class BillRepository extends AbstractEntityRepository<Bill, Integer> {

}
