package com.url.shortener.controllers;


import com.url.shortener.Services.Ten_UserService;
import com.url.shortener.Services.Thirteen_UrlMappingService;
import com.url.shortener.dtos.Fourteen_UrlMappingDto;
import com.url.shortener.dtos.Sixteen_ClickEventDto;
import com.url.shortener.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
public class Twelve_UrlMappingController {

    @Autowired
    private Thirteen_UrlMappingService urlMappingService;
    @Autowired
    private Ten_UserService userService;

    /* Utilizaremos la anotacion @PreAuthorize de Spring para que
     esta ruta solo este disponible cuando el usuario siga registrado
     y tenga el rol de USER
     Usaremos la clase Principal para usar su informacion */
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Fourteen_UrlMappingDto> createShortUrl(@RequestBody Map<String,String> request,
                                                                 Principal principal){
        String originalUrl = request.get("originalUrl");
        User user = userService.findByUsername(principal.getName());
        Fourteen_UrlMappingDto urlMappingDto = urlMappingService.createShortUrl(originalUrl, user);
        return ResponseEntity.ok(urlMappingDto);
    }


    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Fourteen_UrlMappingDto>> getUserUrls(Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Fourteen_UrlMappingDto> urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
    }


    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Sixteen_ClickEventDto>> getUrlAnalytics(@PathVariable String shortUrl,
                                                                       @RequestParam("startDate") String startDate,
                                                                       @RequestParam("endDate") String endDate){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate,formatter);
        LocalDateTime end = LocalDateTime.parse(endDate,formatter);
        List<Sixteen_ClickEventDto> clickEventDtos = urlMappingService.getClickEventsByDate(shortUrl,start,end);

        return ResponseEntity.ok(clickEventDtos);
    }


    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(Principal principal,
                                                                     @RequestParam("startDate") String startDate,
                                                                     @RequestParam("endDate") String endDate){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        User user = userService.findByUsername(principal.getName());

        LocalDate start = LocalDate.parse(startDate,formatter);
        LocalDate end = LocalDate.parse(endDate,formatter);
        Map<LocalDate,Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user,start,end);

        return ResponseEntity.ok(totalClicks);
    }


}
