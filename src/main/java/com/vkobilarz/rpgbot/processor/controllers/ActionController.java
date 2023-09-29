package com.vkobilarz.rpgbot.processor.controllers;

import com.vkobilarz.rpgbot.processor.models.ActionRequest;
import com.vkobilarz.rpgbot.processor.models.ActionResponse;
import com.vkobilarz.rpgbot.processor.services.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/action")
@RestController
@AllArgsConstructor
public class ActionController {
    private final ActionService action;
    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ActionResponse> create(@RequestBody ActionRequest entry) {
        return ResponseEntity.ok().body(action.execute(entry));
    }
}
