package com.example.mailexchange.MailExchange.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MailExchangeSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
/*        http.authorizeRequests()
                .anyRequest().authenticated().and().formLogin()
                .usernameParameter("custUserName").passwordParameter("custUserPassword").loginPage("/validateLogin").loginProcessingUrl("/validateLogin").permitAll()
                .and().logout().logoutSuccessUrl("/validateLogin").invalidateHttpSession(true);*/
http.authorizeRequests().anyRequest().permitAll().and().logout().logoutSuccessUrl("/validateLogin").invalidateHttpSession(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/createAccount","/forgotPassword","/h2/**");
    }
}
