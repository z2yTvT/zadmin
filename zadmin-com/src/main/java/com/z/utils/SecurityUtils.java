package com.z.utils;

import com.z.entity.dto.SecurityUserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class SecurityUtils {

    public static SecurityUserDto getSecurityUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (SecurityUserDto)principal;
    }



    public static String encryptPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


    public static boolean matchesPassword(String rawPassword, String encodedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
