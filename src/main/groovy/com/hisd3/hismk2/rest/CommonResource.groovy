package com.hisd3.hismk2.rest

import com.hisd3.hismk2.dao.PatientDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CommonResource {



    @RequestMapping("/ping")
    String ping(){
        "PONG"
    }

    @RequestMapping("/")
    String index(){




        "WELCOME TO HISMK2 GraphQL Server."


    }
}
