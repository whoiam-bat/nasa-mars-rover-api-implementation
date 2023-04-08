package ua.com.demo.spacey.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ua.com.demo.spacey.service.CustomerService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    private final CustomerService customerService;

    private final SpringConfiguration springConfiguration;

    @Autowired
    public SecurityConfiguration(CustomerService customerService, SpringConfiguration springConfiguration) {
        this.customerService = customerService;
        this.springConfiguration = springConfiguration;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customerService)
                .passwordEncoder(springConfiguration.passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/auth/register", "/auth/login", "/error",
                                    "/css/**", "/images/**", "/js/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                }).formLogin(form -> {
                    form.loginPage("/auth/login")
                            .loginProcessingUrl("/process_login")
                            .defaultSuccessUrl("/api/home", true)
                            .failureUrl("/auth/login?error");
                }) .rememberMe()
                .key("SECRET_UNIQUE")
                .rememberMeParameter("remember") // it is name of checkbox at login page
                .rememberMeCookieName("SPACE-Y") // it is name of the cookie
                .tokenValiditySeconds(86400)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");

        return http.build();
    }
}
