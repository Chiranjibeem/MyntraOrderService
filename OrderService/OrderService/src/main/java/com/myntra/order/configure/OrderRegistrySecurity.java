package com.myntra.order.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class OrderRegistrySecurity extends WebSecurityConfigurerAdapter {

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser("chiranjibee").password("{noop}chiranjibee").roles("USER").and().
                withUser("chintu").password("{noop}chintu").roles("USER");
    }*/

    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;

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
        http.authorizeRequests()
                .antMatchers("**/createOrder/**","**/findOrder/**","**/findOrderByCustomer/**").authenticated()
                .anyRequest().permitAll().and().formLogin().loginPage("/login.html").successForwardUrl("/login").permitAll();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.logout();

    }
}
