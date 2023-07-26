package com.vkobilarz.rpgbot.processor.controllers;

import com.vkobilarz.rpgbot.processor.models.Action;
import com.vkobilarz.rpgbot.processor.models.CreateActionResponse;
import com.vkobilarz.rpgbot.processor.services.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/action")
@RestController
@AllArgsConstructor
public class ActionController {
    private final ActionService actionService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CreateActionResponse> createAction(@RequestBody Action action) throws Exception {
        CreateActionResponse createActionResponse = actionService.executeAction(action);

        return ResponseEntity.ok().body(createActionResponse);
    }
}
