package com.contoso.service.command;

import com.contoso.domain.Customer;
import com.contoso.domain.result.CustomerResult;
import org.springframework.stereotype.Component;

@Component
public class PopulateCustomerResult implements BaseResult<CustomerResult, Customer>{

    @Override
    public CustomerResult populate(Customer customer) {
        CustomerResult result = new CustomerResult();

        result.setId(customer.getId());
        result.setFirstName(customer.getFirstName());
        result.setLastName(customer.getLastName());
        result.setNumber(customer.getNumber());
        result.setEmail(customer.getEmail());

        return result;
    }
}
