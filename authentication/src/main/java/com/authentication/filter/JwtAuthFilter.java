package com.authentication.filter;

import java.io.IOException;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authentication.config.UserInfoUserDetailsService;
import com.authentication.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @Autowired
    private Environment env;
    private String broadcom_communication_token;
    @PostConstruct
    private void postConstruct() {
        this.broadcom_communication_token = env.getProperty("broadcom_communication_token");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(" Auth app header received ,user_id : "+request.getHeader("user_id"));
        System.out.println(" Auth app header received , exp    : "+request.getHeader("exp"));
        System.out.println(" Auth app header received , iat    : "+request.getHeader("iat"));
        System.out.println(" Auth app header received , roles  : "+request.getHeader("roles"));
        System.out.println(" Auth app header received , sub    : "+request.getHeader("sub"));
        System.out.println(" Auth app header received , broadcom_communication_token: "+request.getHeader("broadcom_communication_token"));
        System.out.println(" Auth this service token  :broadcom_communication_token : "+broadcom_communication_token);



        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
