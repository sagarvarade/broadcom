package com.Broadcomapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${broadcom_communication_token}")
    String broadcom_communication_token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(" Broadcom header received , user_id : "+request.getHeader("sub"));
        System.out.println(" Broadcom header received , exp    : "+request.getHeader("exp"));
        System.out.println(" Broadcom header received , iat    : "+request.getHeader("iat"));
        System.out.println(" Broadcom header received , roles  : "+request.getHeader("roles"));
        System.out.println(" Broadcom header received , sub    : "+request.getHeader("sub"));
        System.out.println(" Broadcom header received , broadcom_communication_token: "+request.getHeader("broadcom_communication_token"));
        System.out.println(" Broadcom service token  ,  broadcom_communication_token : "+broadcom_communication_token);
        String tokenForMicroCommunication= request.getHeader("broadcom_communication_token");
        if (!tokenForMicroCommunication.equals(broadcom_communication_token)) {
            throw new RuntimeException("You are not allow to communicate to this services {} " + tokenForMicroCommunication);
        }
        filterChain.doFilter(request, response);
    }
}
