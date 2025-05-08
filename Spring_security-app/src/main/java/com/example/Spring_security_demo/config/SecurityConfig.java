package com.example.Spring_security_demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrf= new Customizer<CsrfConfigurer<HttpSecurity>>() { //     @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//
//            }
//        };
//
//        http.csrf(custCsrf);
         http
                 .csrf(customizer-> customizer.disable())
                 .authorizeHttpRequests(request-> request.anyRequest().authenticated())
     //   http.formLogin(Customizer.withDefaults());
               .httpBasic(Customizer.withDefaults())
               .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
@Autowired
private UserDetailsService userDetailsService;

  @Bean
    public DaoAuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return  provider;
    }

//@Bean
//  public UserDetailsService userDetailsService(){
//
//      UserDetails user= User
//              .withDefaultPasswordEncoder()
//              .username("Ujjwal")
//              .password("ujjwal@123")
//              .roles("user")
//              .build();
//
//      return new InMemoryUserDetailsManager(user);
//  }

}
