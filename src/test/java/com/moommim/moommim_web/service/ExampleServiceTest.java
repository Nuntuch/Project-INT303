package com.moommim.moommim_web.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class ExampleServiceTest {

    private ExampleServiceImpl exampleService;

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
        exampleService = new ExampleServiceImpl();
    }

    @Test
    void testGreeting() {
        String actual = exampleService.getGreeting();
        String expected = "Hello World";
        System.out.println(actual);
        assertEquals(expected, actual);
    }

}
