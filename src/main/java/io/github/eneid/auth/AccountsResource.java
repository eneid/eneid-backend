package io.github.eneid.auth;

import io.github.eneid.community.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountsResource {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    CommunityRepository communityRepository;

    @RequestMapping(
            value = {"login", "login/"},
            method = RequestMethod.GET
    )
    public void login(@RequestParam("login") String login,
                      @RequestParam("password") String password) {

        Account account = accountsRepository.findByEmail(login);
        if (account == null || !encoder.matches(password, account.getPassword())) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @RequestMapping(
            value = {"users", "users/"},
            method = RequestMethod.POST
    )
    public void register(@RequestBody Account user) {

        jdbcTemplate.update(
                "insert into users(username, password, name, first_name, enabled, community) values (?, ?, ?, ?, true, ?);",
                user.getEmail(), encoder.encode(user.getPassword()), user.getName(), user.getFirstName(), user.getCommunity().getId());

        jdbcTemplate.update(
                "insert into authorities(username, authority) values (?, ?);",
                user.getEmail(), "USER");
    }

    @RequestMapping(
            value = {"users", "users/"},
            method = RequestMethod.GET
    )
    public Iterable<Account> findAll() {
        return accountsRepository.findAll();
    }

}
