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
            method = RequestMethod.POST,
            consumes = "application/x-www-form-urlencoded"
    )
    public void update(@RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name,
                       @RequestParam("firstName") String firstName,
                       @RequestParam(value = "communityId", required = false) Long communityId) {
        Account user = new Account();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setFirstName(firstName);
        user.setCommunity(communityRepository.findOne(communityId));
        update(user);
    }

    @RequestMapping(
            value = {"users", "users/"},
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public void update(@RequestBody Account user) {
        jdbcTemplate.update(
                "insert into users(username, password, name, first_name, enabled, community) values (?, ?, ?, ?, true, ?);",
                user.getEmail(), encoder.encode(user.getPassword()), user.getName(), user.getFirstName(), user.getCommunityId());

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
