package io.github.eneid.timeline;


import io.github.eneid.actions.QuickAction;
import io.github.eneid.actions.QuickActionsRepository;
import io.github.eneid.auth.Account;
import io.github.eneid.auth.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;

@RestController
@RequestMapping("/api/")
public class MessagesResource {

    private final MessagesRepository messagesRepository;
    private final AccountsRepository accountRepository;
    private final QuickActionsRepository quickActionRepository;

    @Autowired
    public MessagesResource(MessagesRepository messagesRepository,
                            AccountsRepository accountRepository,
                            QuickActionsRepository quickActionRepository) {
        this.messagesRepository = messagesRepository;
        this.accountRepository = accountRepository;
        this.quickActionRepository = quickActionRepository;
    }


    @PostConstruct
    public void setUp() {
        Account account = accountRepository.findByEmail("o.girardot@lateral-thoughts.com");
        Message message = new Message();
        message.setContents("Je viens de la voir, il a bien mangé aujourd'hui, je repasserai ce soir.");
        message.setDate(new Date());
        message.setAuthor(account);
        messagesRepository.save(message);
    }

    @RequestMapping(value = {"timeline", "timeline/"},
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public Iterable<Message> findAllMessages() {
        return messagesRepository.findAll();
    }

    @RequestMapping(value = {"timeline", "timeline/"},
            method = RequestMethod.POST,
            consumes = "application/json")
    public void pushMessage(@RequestBody Message message,
                            @AuthenticationPrincipal User currentUser) {

        message.setDate(new Date());
        message.setAuthor(accountRepository.findOne(currentUser.getUsername()));
        messagesRepository.save(message);
    }

    @RequestMapping(
            value = {"timeline/action","timeline/action/"},
            method = RequestMethod.POST
    )
    public void putAction(@RequestParam("id") Long id,
                          @AuthenticationPrincipal User currentUser) {
        QuickAction found = quickActionRepository.findOne(id);
        if (found != null ){
            Message message = new Message();
            message.setAuthor(accountRepository.findOne(currentUser.getUsername()));
            message.setContents("");
            message.setDate(new Date());
            message.setAction(found);
            messagesRepository.save(message);
        }
    }
}
