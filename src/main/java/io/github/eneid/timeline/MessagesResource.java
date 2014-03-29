package io.github.eneid.timeline;

import io.github.eneid.auth.Account;
import io.github.eneid.auth.AccountsRepository;
import io.github.eneid.auth.AccountsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;

@RestController
@RequestMapping("/api/timeline")
public class MessagesResource {

    private final MessagesRepository messagesRepository;
    private final AccountsRepository accountRepository;

    @Autowired
    public MessagesResource(MessagesRepository messagesRepository, AccountsRepository accountRepository) {
        this.messagesRepository = messagesRepository;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void setUp() {
        Account account = accountRepository.findOne("jj@gmail.com");
        account.setName("Martin");
        account.setFirstName("Jean jacques");

        accountRepository.save(account);
        Message message = new Message();
        message.setAuthor(account);
        message.setContents("Il est mort !");
        message.setDate(new Date());
        messagesRepository.save(message);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Iterable<Message> helloWorld() {
        return messagesRepository.findAll();
    }
}
