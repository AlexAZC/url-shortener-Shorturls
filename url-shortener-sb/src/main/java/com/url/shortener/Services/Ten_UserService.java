package com.url.shortener.Services;

import com.url.shortener.dtos.Eleven_LoginRequest;
import com.url.shortener.models.User;
import com.url.shortener.repositories.Six_UserRepository;
import com.url.shortener.security.jwt.Three_JwtAuthenticationResponse;
import com.url.shortener.security.jwt.Two_JwtUtils;
import com.url.shortener.security.serviceSec.One_UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class Ten_UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Six_UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Two_JwtUtils jwtUtils;


    /* En este metodo a parte de registrar el usuario, tambien
        encriptamos la constraseña del registro para sea más
         segura*/
    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public Three_JwtAuthenticationResponse authenticateUser(Eleven_LoginRequest loginRequest){
        /*Usaremos la clase authenticacionManager por parte de Spring
          para poder procesar un request retornando una Autenticacion
           adecuada con todos sus atributos y autoridades*/
        Authentication authentication = authenticationManager.authenticate(

        //Usaremos esta clase de Spring para implementar un simple registro
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        /*Le decimos a spring que la informacion del registro
          este pendiente hasta que se acabe la sesion */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        One_UserDetailsImpl userDetails = (One_UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(userDetails);

        return new Three_JwtAuthenticationResponse(jwt);
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username " + name)
        );
    }
}
