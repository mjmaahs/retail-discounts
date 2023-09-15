package com.retaildiscounts.model.mapper;

import com.retaildiscounts.model.dto.BillDTO;
import com.retaildiscounts.model.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {

    public Bill mapToEntity(BillDTO billDTO) {
        Bill bill = new Bill();
        bill.setProductSerialNumbers(billDTO.getProductSerialNumbers());
        bill.setUsername(billDTO.getUsername());
        bill.setTotalAmount(billDTO.getTotalAmount());
        return bill;
    }

}
