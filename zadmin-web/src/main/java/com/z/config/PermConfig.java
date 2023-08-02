package com.z.config;

import org.springframework.stereotype.Service;

@Service(value = "z")
public class PermConfig {

    public Boolean permCheck(String ...permissions){
        return true;
    }
}
