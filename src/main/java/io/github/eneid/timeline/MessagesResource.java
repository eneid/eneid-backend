package io.github.eneid.timeline;


import io.github.eneid.actions.QuickAction;
import io.github.eneid.actions.QuickActionsRepository;
import io.github.eneid.auth.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = {"timeline", "timeline/"},
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public Iterable<Message> findAllMessages() {
        return messagesRepository.findAll();
    }

    @RequestMapping(value = {"timeline", "timeline/"},
            method = RequestMethod.POST)
    public void pushMessage(@RequestParam("content") String contents,
                            @AuthenticationPrincipal User currentUser) {

        Message message = new Message();
        message.setDate(new Date());
        message.setAuthor(accountRepository.findOne(currentUser.getUsername()));
        message.setContents(contents);
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
