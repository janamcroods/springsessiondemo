package com.example.springsessiondemo;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("janam")
                .password("{noop}12345678")
                .roles("USER")
                .and()
                .withUser("John")
                .password("{noop}87654321")
                .roles("MANAGER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.logout().logoutSuccessUrl("/login?logout");
//          http.headers().frameOptions().sameOrigin();
//          http.sessionManagement().invalidSessionUrl("/login?reason=session-expired").maximumSessions(100).
//          maxSessionsPreventsLogin(true);
//          http.sessionManagement().sessionFixation().migrateSession()
//          .sessionAuthenticationStrategy(registerSessionAuthStr());


    }

    @Bean
      public SessionRegistry sessionRegistry() { return new
              SessionRegistryImpl(); }

      @Bean public RegisterSessionAuthenticationStrategy registerSessionAuthStr() {
      return new RegisterSessionAuthenticationStrategy(sessionRegistry()); }

      @Bean public ServletListenerRegistrationBean<HttpSessionEventPublisher>
      httpSessionEventPublisher() { return new
              ServletListenerRegistrationBean<>(new HttpSessionEventPublisher()); }


}
