package com.yener.microlearning.security;

import com.yener.microlearning.dto.user.CurrentUser;
import com.yener.microlearning.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.yener.microlearning.security.SecurityConstants.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private UserService userService;

    public AuthorizationFilter(AuthenticationManager authManager,UserService userService) {
        super(authManager);
        this.userService=userService;
    }

    //Bu method, korunan bir kaynağa request geldiğinde bu request’i yakalar,
    // JWT’yi validate eder ve herşey uygunsa kullanıcı authentication bilgilerini
    // SecurityContextHolder ile SecurityContext’in içine yazar ve kaynağı client’a sunar.
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // AuthenticationFilter sınıfındaki successfulAuthentication() methoduyla benzer,
    // Authorization Header’ından JWT değerini okur ve token’in geçerliliğini onaylar.
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String email = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            CurrentUser currentUser= (CurrentUser) userService.loadUserByUsername(email);
            if (email != null) {
                return new UsernamePasswordAuthenticationToken(currentUser, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

}
