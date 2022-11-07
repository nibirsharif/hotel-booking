package com.contoso.service.command;

import com.contoso.domain.Customer;
import com.contoso.domain.command.CreateCustomerCommand;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomer implements BaseModel<Customer, CreateCustomerCommand> {

    @Override
    public Customer create(CreateCustomerCommand createCustomerCommand) throws Throwable {
        try {
            Customer customer = new Customer();
            customer.setFirstName(createCustomerCommand.getFirstName());
            customer.setLastName(createCustomerCommand.getLastName());
            customer.setNumber(createCustomerCommand.getNumber());
            customer.setEmail(createCustomerCommand.getEmail());

            return customer;
        } catch (Exception e) {
            throw new Exception("Exception while creating order. " + e.getMessage());
        }
    }
}
