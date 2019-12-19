package com.frame4j.mq.service;

import com.frame4j.mq.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    public boolean insertApplication(Map application) {
        return applicationMapper.insertApplication(application);
    }

    public Map getApplication(String id) {
        return applicationMapper.getApplication(id);
    }

}
