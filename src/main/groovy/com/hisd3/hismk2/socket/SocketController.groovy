package com.hisd3.hismk2.socket

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class SocketController {

    //socket receiver from the frontend '/app/ws'

    @Autowired
    ObjectMapper objectMapper

    @MessageMapping("/ws")
    @SendTo("/topic/hello")
    protected String hello(String world) {
        return "Hello Message From Backend, ${world}!"
    }

    @MessageMapping("/ws/{topic}")
    @SendTo("/topic/messages")
    Message send(@DestinationVariable("topic") String topic,
                 Map<String, Object> message)  throws Exception
    {
        def msg
        msg = objectMapper.convertValue(message, Message.class)
        msg.topic =topic
        return msg
    }
}