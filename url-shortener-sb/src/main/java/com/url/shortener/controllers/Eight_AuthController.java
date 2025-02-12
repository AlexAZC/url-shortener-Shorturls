package com.url.shortener.controllers;


import com.url.shortener.Services.Ten_UserService;
import com.url.shortener.dtos.Eleven_LoginRequest;
import com.url.shortener.dtos.Nine_RegisterRequest;
import com.url.shortener.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//Esta ruta es el login el cual vamos a registrar nuestro username y password
@RestController
@RequestMapping("/api/auth")

public class Eight_AuthController {

    @Autowired
    private Ten_UserService userService;

    //Esta ruta sirve para que las personas puedan crear una cuenta
    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody Nine_RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setRole("ROLE_USER");
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }


    //Esta ruta es para que nuestros usuarios puedan ingresar al login
    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody Eleven_LoginRequest loginRequest){
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }




    }
