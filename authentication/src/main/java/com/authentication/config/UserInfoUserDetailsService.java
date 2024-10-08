package com.authentication.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.authentication.entity.UserInfo;
import com.authentication.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("broadcast not found " + username));
    }
    
    public Optional<UserInfo> loadUserDetails(String username) {
        return repository.findByName(username);
    }
}
