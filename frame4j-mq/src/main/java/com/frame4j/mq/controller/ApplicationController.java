package com.frame4j.mq.controller;

import com.frame4j.common.utils.Frame4JResult;
import com.frame4j.common.utils.IdGen;
import com.frame4j.mq.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("saveApplication")
    public Frame4JResult saveApplication(String id, String uuids, String json) {
        Map<String, String> obj = new HashMap<>();
        obj.put("id", id);
        obj.put("uuids", uuids);
        obj.put("json", json);
        boolean result = applicationService.insertApplication(obj);
        if (result) {
            return Frame4JResult.build(200, "添加成功!");
        } else {
            return Frame4JResult.build(510, "添加失败!");
        }
    }

    @GetMapping("loadApplication")
    public Frame4JResult loadApplication() {
        IdGen idGen = new IdGen();
        long id = idGen.nextId();
        return Frame4JResult.ok(String.valueOf(id));
    }

    @GetMapping("getDataset")
    public Frame4JResult getDataset(String id) {
        Map application = applicationService.getApplication(id);
        return Frame4JResult.ok(application);
    }


}
