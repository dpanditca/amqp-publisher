package com.pandit.controller;

import com.pandit.model.CustomMessage;
import com.pandit.publisher.MessagePublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublishController {

    @NonNull
    private final MessagePublisher messagePublisher;

    @PostMapping("/{delay}")
    public void postMessage(@RequestBody CustomMessage message, @PathVariable Integer delay) {
        messagePublisher.publishMessage(message, delay);
    }
}
