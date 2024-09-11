package com.authentication.controller;

import com.authentication.config.UserInfoUserDetailsService;
import com.authentication.dto.AuthRequest;
import com.authentication.entity.UserInfo;
import com.authentication.service.JwtService;
import com.authentication.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Welcome this endpoint is not secure",HttpStatus.OK);
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
    public ResponseEntity<Map<String, Object>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
 
        String token=jwtService.generateToken(authRequest.getUsername());
        System.out.println("Checking Toke : "+token);
        if (authentication.isAuthenticated()) {
        	Map<String, Object> response = new HashMap<>();
        	UserInfo user = userInfoService.loadUserDetails(authRequest.getUsername()).orElse(new UserInfo());
            response.put("username", user.getName());
            response.put("user_id", user.getId());
            response.put("email", user.getEmail());
            response.put("roles", user.getRoles());
            response.put("token", token);
            return  new ResponseEntity<>(response,HttpStatus.OK);
        } else {
        	Map<String, Object> response = new HashMap<>();
            response.put("error","Invalid credentials");
            return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/test-token")
    public ResponseEntity<String> index() {
    	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
    	if(!(auth instanceof AnonymousAuthenticationToken)) {
    		return new ResponseEntity<>("TOKEN_IS_VALID",HttpStatus.OK);
    	}
        return new ResponseEntity<>("TOKEN_NOT_VALID",HttpStatus.BAD_REQUEST);
    }
}
