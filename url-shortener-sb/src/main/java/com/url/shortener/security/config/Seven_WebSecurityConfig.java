package com.url.shortener.security.config;

import com.url.shortener.security.jwt.Four_JwtAuthenticationFilter;
import com.url.shortener.security.serviceSec.Five_UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/* Esta clase definira las reglas de seguridad para poder manejar requests
    del usuario */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class Seven_WebSecurityConfig {

    @Autowired
    private Five_UserDetailsServiceImpl userDetailsService;

    @Bean
    public Four_JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new Four_JwtAuthenticationFilter();
    }

    //Este metodo nos ayuda a encriptar el codigo
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /* Este metodo Dao nos ayuda a indicarle a Spring Security
       de como la autenticacion debe ser manejada, en este caso
       sera por nuestra base de datos     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(@org.jetbrains.annotations.NotNull HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/urls/**").authenticated()
                                .requestMatchers("/{shortUrl}").permitAll()
                                .anyRequest().authenticated()
                );


        http.authenticationProvider(authenticationProvider());

        /*Le decimos a Spring que antes del filtro UsernamePassword...
          haga el filtro que creamos jwtAuthenticacionFilter  */
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }





}
