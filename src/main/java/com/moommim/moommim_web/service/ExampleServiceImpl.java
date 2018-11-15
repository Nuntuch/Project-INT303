package com.moommim.moommim_web.service;

import com.moommim.moommim_web.service.base.ExampleService;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleServiceImpl implements ExampleService {

    @Override
    public String getGreeting() {
        return "Hello World";
    }

}
