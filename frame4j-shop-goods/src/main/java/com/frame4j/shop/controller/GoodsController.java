package com.frame4j.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/")
public class GoodsController {

    @GetMapping("get")
    public String getGoodsById(String id) {
        return "获取商品成功，id：" + id;
    }
}
