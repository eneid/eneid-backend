package io.github.eneid.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountsResource {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AccountsRepository repository;

    @RequestMapping(
            value = {"login", "login/"},
            method = RequestMethod.GET
    )
    public String logMeIn(@RequestParam("login") String login,
                          @RequestParam("password") String password) {

        return "login " + login + " passwd " + password;
    }

    @RequestMapping(
            value = {"users", "users/"},
            method = RequestMethod.POST
    )
    public void update(@RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name,
                       @RequestParam("firstName") String firstName
    ) {
        jdbcTemplate.update(
                "insert into users(username, password, name, first_name, enabled) values (?, ?, ?, ?, true);",
                email, encoder.encode(password), name, firstName);

        jdbcTemplate.update(
                "insert into authorities(username, authority) values (?, ?);",
                email, "USER");
    }

    @RequestMapping(
            value = {"users", "users/"},
            method = RequestMethod.GET
    )
    public Iterable<Account> findAll() {
        return repository.findAll();
    }

}
