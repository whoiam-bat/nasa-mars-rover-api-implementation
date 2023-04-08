package ua.com.demo.spacey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.demo.spacey.model.Customer;
import ua.com.demo.spacey.service.CustomerService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserAdminController {

    private final CustomerService customerService;

    @Autowired
    public UserAdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getUsersList(Model model, Principal principal) {
        Customer customer = customerService.loadUserByUsername(principal.getName());

        List<Customer> customerList = customerService.findAllWhereRoleLessThanGiven(customer);

        model.addAttribute("customerList", customerList);
        model.addAttribute("principalCustomer", customer);

        return "admin-panel";
    }

    @PostMapping("/make-admin")
    public String makeAdmin(@RequestParam int customerId) {
        Customer customer = customerService.findOne(customerId);
        customer.setRoleName("ROLE_ADMIN");
        customerService.update(customer);

        return "redirect:/admin";
    }

    @PostMapping("/remove-admin")
    public String removeAdmin(@RequestParam int customerId) {
        Customer customer = customerService.findOne(customerId);
        customer.setRoleName("ROLE_USER");
        customerService.update(customer);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteCustomer(@RequestParam int customerId) {
        customerService.remove(customerId);

        return "redirect:/admin";
    }
}
