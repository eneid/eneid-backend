package io.github.eneid.timeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timeline")
public class MessagesResource {

    private final MessagesRepository messagesRepository;

    @Autowired
    public MessagesResource(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Iterable<Message> helloWorld() {
        return messagesRepository.findAll();
    }
}
