package com.frame4j.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/manager/")
public class ManagerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("createOrder")
    public String create(){
        String url = "http://frame4j-shop-order/order/create";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("管理调用消息服务rsult:" + result);
        return "创建订单成功!";
    }
}
