package com.yener.microlearning.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yener.microlearning.dto.user.UserLoginRequestDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.yener.microlearning.security.SecurityConstants.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //Bu sınıfımızın iki görevi var.
    // * Başarılı login’lere JWT atama
    // * yeni authorized requestlerin JWT’sini kontrol etme.

    private AuthenticationManager authenticationManager;


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // attemptAuthentication fonsiyonunda gelen request’teki Header’i parse edip Token’i alıyoruz.
    // Ardından UsernamePasswordAuthenticationToken sınıfının bir nesnesini oluşturup JWT’mizi set ediyoruz.
    // Nesnesini oluşturduğumuz bu sınıf kullanıcı adı ve parolayı saklar.
    // getCredentials() ve getPrinciples() methodları ile bunlara erişebiliriz.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserLoginRequestDTO creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserLoginRequestDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // succesfulAuthentication ile başarılı bir şekilde login olmuş kullanıcıya JWT yollayacağız.
    // Bunun için Jwts sınıfının builder() methodu ile Payload kısmı için subject ve expiration time’i set edip,
    // kendi tanımladığımız SECRET ile imzalayıp Tokenimizi oluşturuyoruz ve Header’a ekliyoruz.
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String userName = ((User) auth.getPrincipal()).getUsername();

        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    //EXPIRATIONITIME = Token’in geçerlilik süresi
    //SECRET= Token’in HMAC algoritması ile imzalanırken kulladığı secret key, istediğimiz değeri verebiliriz.
    //TokenPrefix =Authorization Header’ındaki auth tipi.(basic, bearer, ..)
}
