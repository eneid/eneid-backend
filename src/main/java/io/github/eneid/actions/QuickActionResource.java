package io.github.eneid.actions;

import io.github.eneid.actions.domain.QuickAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@RestController
@RequestMapping("/api/actions")
public class QuickActionResource {

    @RequestMapping(
            value = {"","/"},
            method = RequestMethod.GET
    )
    public List<QuickAction> getAvailableActions() {
        return newArrayList();

    }
}
