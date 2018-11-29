package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.Bill;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class BillRepository extends AbstractEntityRepository<Bill, Integer> {

    public abstract List<Bill> findOptionalByUserId_id(int userId);

}
