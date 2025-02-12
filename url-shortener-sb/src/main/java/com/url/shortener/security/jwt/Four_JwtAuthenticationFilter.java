package com.url.shortener.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/* Esta clase es para estar seguros de que cada request por parte del
 usuario tenga Jwt en el*/
@Component
public class Four_JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private Two_JwtUtils jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;



    /* Este metodo sirve para añadir operaciones que nos ayude
    a autenticar el request */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            //Obten el JWT del Header
            String jwt = jwtTokenProvider.getJwtFromHeader(request);
            //Valida el token
            if(jwt != null && jwtTokenProvider.validateToken(jwt)){
                //Si es valido da los detalles del usuario
            // obten el nombre de usuario  -> carga el usuario -> marca el contexto de autenticacion
                String username = jwtTokenProvider.getUsernameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(userDetails != null ){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }

        filterChain.doFilter(request,response);

    }











}
