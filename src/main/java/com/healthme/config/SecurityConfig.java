package com.healthme.config;


import com.healthme.service.admin.MyAdminDetailsService;
import com.healthme.service.doctor.MyDoctorDetailsService;
import com.healthme.service.patient.MyPatientDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@ComponentScan("com.healthme")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService patientDetailsService() {
        return new MyPatientDetailsService();
    }

    @Bean
    public UserDetailsService doctorDetailsService() {
        return new MyDoctorDetailsService();
    }

    @Bean
    public UserDetailsService adminDetailsService() {
        return new MyAdminDetailsService();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(patientAuthenticationProvider());
        auth.authenticationProvider(doctorAuthenticationProvider());
        auth.authenticationProvider(adminAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider patientAuthenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(patientDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider doctorAuthenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(doctorDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(adminDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/patient/register").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/patient/**").hasRole("PATIENT")
                .antMatchers("/doctor/**").hasRole("DOCTOR")
                .and()
                .formLogin().loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/user/login?error=true")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
