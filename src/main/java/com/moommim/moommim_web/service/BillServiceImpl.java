package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.repository.BillRepository;
import com.moommim.moommim_web.service.base.BillService;
import com.moommim.moommim_web.util.Util;
import java.util.List;
import javax.inject.Inject;

public class BillServiceImpl implements BillService {

    @Inject
    BillRepository billRepository;

    @Override
    public Bill create(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getBillByUserId(int userId) {
        return billRepository.findOptionalByUser_Id_id(userId);
    }

}
