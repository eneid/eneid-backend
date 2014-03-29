package io.github.eneid.timeline;

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

    @Autowired
    public MessagesResource(MessagesRepository messagesRepository, AccountsRepository accountRepository) {
        this.messagesRepository = messagesRepository;
        this.accountRepository = accountRepository;
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
}
