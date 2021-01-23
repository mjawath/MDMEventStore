package com.techstart.messaging.kafka.kafkaeventstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventStreamService eventStreamService;

    @PostMapping("/produce")
    public void sendEvent(@RequestBody Message msg) throws Exception {
        eventStreamService.produceEvent(msg);
    }

}