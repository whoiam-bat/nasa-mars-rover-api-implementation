package ua.com.demo.spacey.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.demo.spacey.model.Customer;
import ua.com.demo.spacey.service.CustomerService;
import ua.com.demo.spacey.util.CustomerValidator;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final CustomerValidator customerValidator;

    private final CustomerService customerService;

    @Autowired
    public AuthenticationController(CustomerValidator customerValidator, CustomerService customerService) {
        this.customerValidator = customerValidator;
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("customer") Customer customer) {
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {

        customerValidator.validate(customer, bindingResult);

        if(bindingResult.hasErrors()) return "/registration";

        customerService.save(customer);

        return "redirect:/auth/login";
    }

}
