package com.authentication.filter;

import com.authentication.config.UserInfoUserDetailsService;
import com.authentication.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserInfoUserDetailsService userDetailsService;

    @Value("${broadcom_communication_token}")
    String broadcom_communication_token;

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            log.info(" Auth app header received ,user_id : {} ", request.getHeader("sub"));
            log.info(" Auth app header received , exp    : {} ", request.getHeader("exp"));
            log.info(" Auth app header received , iat    : {} ", request.getHeader("iat"));
            log.info(" Auth app header received , roles  : {} ", request.getHeader("roles"));
            log.info(" Auth app header received , broadcom_communication_token : {} ", request.getHeader("broadcom_communication_token"));
            log.info(" Auth this service token  , broadcom_communication_token : {} ", broadcom_communication_token);

            String tokenForMicroCommunication = request.getHeader("broadcom_communication_token");
            if (!broadcom_communication_token.equals(tokenForMicroCommunication)) {
                throw new Exception("You are not allow to communicate to this services , Token for communication either not available or not matching.");
            }

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
        catch(Exception ex){
            ex.printStackTrace();
            log.info(" Error in Auth Filter : "+ex.getMessage());
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ((HttpServletResponse) response).setContentType("application/json");
            ((HttpServletResponse) response).getWriter().write("{ \"error\": \"An error occurred: " + ex.getMessage() + "\" }");
        }
    }
}
