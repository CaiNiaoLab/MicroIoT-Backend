package com.frame4j.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("create")
    public String create(){
        String url = "http://frame4j-shop-goods/goods/get";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(" 订单调用消息服务result:" + result);
        return "创建生成订单成功!";
    }
}
