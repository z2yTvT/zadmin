package com.z.config;

import com.z.entity.dto.SecurityUserDto;
import com.z.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "z")
public class PermConfig {

    public Boolean permCheck(String... permissions) {
        SecurityUserDto curUser = SecurityUtils.getSecurityUser();
        List<String> perms = curUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Arrays.stream(permissions).anyMatch(perms::contains) || perms.contains("admin");
    }

}
