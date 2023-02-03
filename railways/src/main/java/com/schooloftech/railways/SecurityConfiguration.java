package com.schooloftech.railways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired    
    public AuthenticationSuccessHandler customSuccessHandler;
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailServiceImp();
    }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
     
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
 
    return authProvider;
    }
    


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests().requestMatchers("/railwayticketing/**").permitAll()
                    // .requestMatchers("/user/**").authenticated()
                    // .requestMatchers("/user/**").hasRole("USER")
                    // .requestMatchers("/finance/**").hasRole("FIN")
                    // .requestMatchers("/schedule/**").hasRole("SCHED")
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .loginProcessingUrl("/doLogin")
                    // .defaultSuccessUrl("/user")
                    .successHandler(customSuccessHandler)
                    .permitAll()
                    .and()
                    .logout().logoutSuccessUrl("/home").permitAll();
            return http.build();
        }



    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
 
}
} 