package ua.com.demo.spacey.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.demo.spacey.configuration.SpringConfiguration;
import ua.com.demo.spacey.model.Customer;
import ua.com.demo.spacey.model.Preference;
import ua.com.demo.spacey.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final PreferenceService preferenceService;

    private final SpringConfiguration springConfiguration;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PreferenceService preferenceService, SpringConfiguration springConfiguration) {
        this.customerRepository = customerRepository;
        this.preferenceService = preferenceService;
        this.springConfiguration = springConfiguration;
    }


    public List<Customer> findAll() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, "customerId"));
    }

    public Customer findOne(int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        return customer.orElse(null);
    }

    @Transactional
    public void save(Customer customer) {
        customer.setPassword(springConfiguration.passwordEncoder().encode(customer.getPassword()));
        customer.setDateCreated(LocalDate.now());
        customer.setRoleName("ROLE_USER");
        customerRepository.save(customer);
    }

    public List<Customer> findAllWhereRoleLessThanGiven(Customer customer) {
        List<Customer> customers = findAll();

        if (customer.getRoleName().equals("ROLE_OWNER")) return customers;

        return customers.stream().filter(it -> !it.getRoleName().equals("ROLE_OWNER")).toList();
    }

    @Transactional
    public int addCustomerPreference(Customer customer, Preference preference) {
        Preference savedPreference = preferenceService.save(preference);
        customer.setPreference(preference);

        return savedPreference.getPreferenceId();
    }

    @Transactional
    public void update(Customer customer) {
        customer.setDateUpdated(LocalDate.now());
        customerRepository.save(customer);
    }

    @Transactional
    public void remove(int customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findCustomerByLogin(username);
        return customer.orElse(null);
    }
}
