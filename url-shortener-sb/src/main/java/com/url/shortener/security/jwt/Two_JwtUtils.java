package com.url.shortener.security.jwt;

/* Este es el archivo del cual va a tener todos los metodos de utilidad que
 nuestra app va a necesitar */


import com.url.shortener.security.serviceSec.One_UserDetailsImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class Two_JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    //Este metodo nos ayudara a extraer el Token del Header para validarlo o no
    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }


    /*Este metodo sirve para crear una llave para poder generar el Token
       despues */
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    //Este metodo nos ayudara a crear o generar el Token
    public String generateToken(One_UserDetailsImpl oneUserDetails){
        /* Necesito el objeto One_UserDetailsImpl porque quiero tener su
        username y su respectivo rol en el Token cuando lo generemos */
        String username = oneUserDetails.getUsername();
        String roles = oneUserDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(username)
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date((new Date().getTime() + jwtExpirationMs)))
                .signWith(key())
                .compact();
    }


    //Este metodo nos dara el nombre del usuario del Token
    public String getUsernameFromJwtToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }


    //Este metodo valida el Token
    public boolean validateToken(String authToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key())
                    .build().parseSignedClaims(authToken);
            return true;
        } catch (JwtException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







}
