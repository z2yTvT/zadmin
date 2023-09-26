package com.z.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    @PreAuthorize("@z.permCheck('test:read')")
    public String test(){
        return "test";
    }
}
