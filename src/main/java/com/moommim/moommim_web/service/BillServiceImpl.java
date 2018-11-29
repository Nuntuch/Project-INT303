package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.repository.BillRepository;
import com.moommim.moommim_web.service.base.BillService;
import com.moommim.moommim_web.util.Util;
import javax.inject.Inject;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class BillServiceImpl implements BillService {

    @Inject
    BillRepository billRepository;

    @Override
    public boolean AddBillToDB(Bill bill) {
        return Util.isNotEmpty(billRepository.save(bill));
    }

}
