package com.telusko.SpringSecEx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.telusko.SpringSecEx.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity   //to tell spring not with default security but use our security
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/login").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}

/* 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
 
        
      //   http.csrf(customizer->customizer.disable());
    //     http.authorizeHttpRequests(request->request.anyRequest().authenticated());
    //    // http.formLogin(Customizer.withDefaults()); //for browser
    //     http.httpBasic(Customizer.withDefaults()); //for postman
    //     http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    

    //similar to lambda  at first line
    //    Customizer<CsrfConfigurer<HttpSecurity>> custCsrf=new Customizer<CsrfConfigurer<HttpSecurity>>() {
    //           @Override
    //           public void customize(CsrfConfigurer<HttpSecurity> customizer) {
    //             customizer.disable();  //to disable csrf
    //           }
    //    };
    //    http.csrf(custCsrf);


    //using builder

   return http.csrf(customizer->customizer.disable())
        .authorizeHttpRequests(request->request
           .requestMatchers("/register","login")
           .permitAll()
           .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults()) //for postman
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();

       // return http.build();  //it return SecurityFilter object
    }*/


    //to verify the user and password the way want we use this userDetailsService
    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1 = User.withDefaultPasswordEncoder()
    //                             .username("kiran")
    //                             .password("k@123")
    //                             .roles("USER")
    //                             .build();

    //     UserDetails user2 = User.withDefaultPasswordEncoder()
    //                             .username("Harsh")
    //                             .password("h@123")
    //                             .roles("ADMIN")
    //                             .build();
    //     return new InMemoryUserDetailsManager(user1,user2); //this class uses userDetailsService
    // }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();

        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
         provider.setPasswordEncoder(new BCryptPasswordEncoder(12));  //but now normal password like n@123 would not work
         provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    //the authentication manager manages authentication provider. we are using this for jwt
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager(); // we get hold on authentication manager
    }

    //Authentication manager will use the configuraation we defined for authentication of user

}
