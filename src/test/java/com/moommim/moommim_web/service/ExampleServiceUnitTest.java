package com.moommim.moommim_web.service;

import com.moommim.moommim_web.service.base.ExampleService;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleServiceUnitTest {

    @Test
    public void testGetGreetingMustReturnString() {
        ExampleService exampleService = new ExampleServiceImpl();
        String geetingText = exampleService.getGreeting();
        assertEquals("Hello World", geetingText);
    }
}
