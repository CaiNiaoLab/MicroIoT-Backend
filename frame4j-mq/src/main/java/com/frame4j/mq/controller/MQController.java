package com.frame4j.mq.controller;

import com.frame4j.mq.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MQController {

    @Autowired
    private SendService sendService;

    @GetMapping("/send")
    public String send(){
        sendService.send();
        return "success";
    }
}
