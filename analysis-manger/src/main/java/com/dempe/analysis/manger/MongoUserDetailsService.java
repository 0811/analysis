/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dempe.analysis.manger;

import com.dempe.analysis.manger.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anjana.m
 */
@Component
public class MongoUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoUserDetailsService.class);

    private org.springframework.security.core.userdetails.User userdetails;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        com.dempe.analysis.manger.system.model.User user = getUserDetail(username);


        userdetails = new User(user.getName(),
                user.getPwd(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user.getRoleId()));
        return userdetails;
    }

    public List<GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role.intValue() == 1) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else if (role.intValue() == 2) {
            authList.add(new SimpleGrantedAuthority("ROLE_MANGER"));
        }
        return authList;
    }

    public com.dempe.analysis.manger.system.model.User getUserDetail(String username) {
        com.dempe.analysis.manger.system.model.User user = userService.findByName(username);
        return user;
    }


}