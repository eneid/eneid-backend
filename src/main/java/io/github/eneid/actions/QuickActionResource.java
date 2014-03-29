package io.github.eneid.actions;

import io.github.eneid.actions.domain.QuickAction;
import io.github.eneid.actions.repository.QuickActionRepository;
import io.github.eneid.auth.AccountsRepository;
import io.github.eneid.timeline.Message;
import io.github.eneid.timeline.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/actions")
public class QuickActionResource {

    private final QuickActionRepository quickActionRepository;
    private final MessagesRepository messagesRepository;
    private final AccountsRepository accountsRepository;

    @Autowired
    public QuickActionResource(QuickActionRepository quickActionRepository,
                               MessagesRepository messagesRepository,
                               AccountsRepository accountsRepository) {
        this.quickActionRepository = quickActionRepository;
        this.messagesRepository = messagesRepository;
        this.accountsRepository = accountsRepository;
    }

    @RequestMapping(
            value = {"","/"},
            method = RequestMethod.GET
    )
    public Iterable<QuickAction> getAvailableActions() {
        return quickActionRepository.findAll();

    }

    @RequestMapping(
            value = {"","/"},
            method = RequestMethod.POST
    )
    public void save(@RequestParam("name") String name) {
        quickActionRepository.save(new QuickAction(name));

    }


    @RequestMapping(
            value = {"test","test/"},
            method = RequestMethod.POST
    )
    public void putAction(@RequestParam("id") Long id,
                                       @AuthenticationPrincipal User currentUser) {
        QuickAction found = quickActionRepository.findOne(id);
        if (found != null ){
            Message message = new Message();
            message.setAuthor(accountsRepository.findOne(currentUser.getUsername()));
            message.setContents("");
            message.setDate(new Date());
            message.setAction(found);
            messagesRepository.save(message);
        }
    }
}
