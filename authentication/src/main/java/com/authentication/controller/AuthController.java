package com.authentication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.config.UserInfoUserDetailsService;
import com.authentication.dto.AuthRequest;
import com.authentication.entity.UserInfo;
import com.authentication.service.JwtService;
import com.authentication.service.UserAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoUserDetailsService userInfoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }


    @GetMapping("/check-role-admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> checkRoleAdmin() {
        return ResponseEntity.ok("Access By role Admin");
    }

    @GetMapping("/check-role-user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> checkRoleUser() {
        return ResponseEntity.ok("Access By ROLE_USER");
    }

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "*")
    public Map<String, Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
 
        String token=jwtService.generateToken(authRequest.getUsername());
        System.out.println(">>"+token);
        
        if (authentication.isAuthenticated()) {
        	Map<String, Object> response = new HashMap<>();
        	UserInfo user = userInfoService.loadUserDetails(authRequest.getUsername()).orElse(new UserInfo());
            response.put("username", user.getName());
            response.put("user_id", user.getId());
            response.put("email", user.getEmail());
            response.put("roles", user.getRoles());
            response.put("token", token);
            return response;
        } else {
        	Map<String, Object> response = new HashMap<>();
            response.put("error","Invalid credentials");
            return response;
        }
    }
    
    @GetMapping("/test-token")
    public String index() {
    	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
    	if(!(auth instanceof AnonymousAuthenticationToken)) {
    		return "TOKEN_IS_VALID";
    	}
        return "TOKEN_NOT_VALID";
    }
}
