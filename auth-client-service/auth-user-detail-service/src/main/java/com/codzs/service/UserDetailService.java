package com.codzs.service;

import com.codzs.entity.Authority;
import com.codzs.entity.User;
import com.codzs.repository.AuthorityRepository;
import com.codzs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDetailService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<Authority> authorities = authorityRepository.findByUsername(username);

        Set<GrantedAuthority> grantAuthorities = new HashSet<GrantedAuthority>();

        for (Authority authority : authorities) {
            grantAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantAuthorities);
    }
}
