package com.hisd3.hismk2.socket

import groovy.transform.TypeChecked
import io.leangen.graphql.spqr.spring.autoconfigure.SpqrProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
@TypeChecked
class SocketService {

    @Autowired
    SocketController socketController

    @Autowired
    SimpMessageSendingOperations brokerMessagingTemplate

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate

    void helloWithPayload(Message payload) {
        simpMessagingTemplate.convertAndSend("/channel/hello", payload)
    }

    void helloToUser(Message payload,String user = 'admin') {
        simpMessagingTemplate.convertAndSendToUser(user,"/channel/hello", payload)
    }

}