package ua.com.demo.spacey.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.demo.spacey.model.Customer;
import ua.com.demo.spacey.service.CustomerService;

@Component
public class CustomerValidator implements Validator {

    private final CustomerService customerService;

    @Autowired
    public CustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        if (customerService.loadUserByUsername(customer.getLogin()) != null)
            errors.rejectValue("login", "", "Customer with such username already exists");
    }
}
