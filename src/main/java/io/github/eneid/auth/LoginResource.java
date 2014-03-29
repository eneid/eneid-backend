package io.github.eneid.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginResource {

    @RequestMapping(
            value = {"", "/"},
            method = RequestMethod.GET
    )
    public String logMeIn(@RequestParam("login") String login,
                          @RequestParam("password") String password) {
        
        return "login " + login + " passwd " + password;
    }
}
