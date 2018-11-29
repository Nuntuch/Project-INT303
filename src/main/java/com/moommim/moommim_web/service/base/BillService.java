package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.Bill;
import java.util.List;

public interface BillService {

    Bill create(Bill bill);

    List<Bill> getBillByUserId(int userId);

}
