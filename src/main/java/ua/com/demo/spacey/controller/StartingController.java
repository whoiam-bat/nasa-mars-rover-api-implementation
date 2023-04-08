package ua.com.demo.spacey.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.demo.spacey.configuration.SpringConfiguration;
import ua.com.demo.spacey.model.Customer;
import ua.com.demo.spacey.model.Preference;
import ua.com.demo.spacey.service.CustomerService;
import ua.com.demo.spacey.service.PreferenceService;
import ua.com.demo.spacey.util.CustomerValidator;
import ua.com.demo.spacey.util.UrlParser;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;

@Controller
@RequestMapping("/api")
public class StartingController {

    private final UrlParser urlParser;

    private final CustomerService customerService;

    private final PreferenceService preferenceService;

    private final SpringConfiguration springConfiguration;

    private final CustomerValidator customerValidator;

    @Autowired
    public StartingController(UrlParser urlParser, CustomerService customerService, PreferenceService preferenceService, SpringConfiguration springConfiguration, CustomerValidator customerValidator) {
        this.urlParser = urlParser;
        this.customerService = customerService;
        this.preferenceService = preferenceService;
        this.springConfiguration = springConfiguration;
        this.customerValidator = customerValidator;
    }

    @GetMapping("/home")
    public String getHomePage(Model model, @RequestParam(name = "preferenceId", required = false) Integer preferenceId,
                              Principal principal)
            throws InvocationTargetException, IllegalAccessException {
        Customer customer = customerService.loadUserByUsername(principal.getName());

        Preference preference = preferenceId == null ?
                new Preference("Curiosity", 1) :
                preferenceService.findOne(preferenceId);

        model.addAttribute("principalCustomer", customer);
        model.addAttribute("roverResponseData", urlParser.getDataFromUrl(preference));
        model.addAttribute("preference", preference);
        model.addAttribute("validCameras", urlParser.getValidCameras().get(preference.getRoverType()));

        return "index";
    }

    @PostMapping("/get")
    public String savePreferences(@ModelAttribute Preference preference, Principal principal) {
        Customer customer = customerService.loadUserByUsername(principal.getName());

        int preferenceId = customerService.addCustomerPreference(customer, preference);

        return "redirect:/api/home?preferenceId=" + preferenceId;
    }

    @GetMapping("/edit")
    public String editPage(Principal principal, Model model) {
        Customer customerToEdit = customerService.loadUserByUsername(principal.getName());
        model.addAttribute("customerToEdit", customerToEdit);

        return "edit-page";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("customerToEdit") @Valid Customer customerToEdit, BindingResult bindingResult) {
        customerValidator.validate(customerToEdit, bindingResult);

        if (bindingResult.hasErrors()) return "/edit-page";

        customerToEdit.setPassword(springConfiguration.passwordEncoder().encode(customerToEdit.getPassword()));

        customerService.update(customerToEdit);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customerToEdit,
                customerToEdit.getPassword(),
                customerToEdit.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/api/home";
    }
}
