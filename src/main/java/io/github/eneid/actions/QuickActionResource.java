package io.github.eneid.actions;

import io.github.eneid.auth.AccountsRepository;
import io.github.eneid.timeline.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actions")
public class QuickActionResource {

    private final QuickActionsRepository quickActionsRepository;
    private final MessagesRepository messagesRepository;
    private final AccountsRepository accountsRepository;

    @Autowired
    public QuickActionResource(QuickActionsRepository quickActionsRepository,
                               MessagesRepository messagesRepository,
                               AccountsRepository accountsRepository) {
        this.quickActionsRepository = quickActionsRepository;
        this.messagesRepository = messagesRepository;
        this.accountsRepository = accountsRepository;
    }

    @RequestMapping(
            value = {"","/"},
            method = RequestMethod.GET
    )
    public Iterable<QuickAction> getAvailableActions() {
        return quickActionsRepository.findAll();

    }

    @RequestMapping(
            value = {"","/"},
            method = RequestMethod.POST
    )
    public void save(@RequestParam("name") String name) {
        quickActionsRepository.save(new QuickAction(name));

    }
}
