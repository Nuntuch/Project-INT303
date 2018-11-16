package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Path;
import com.moommim.moommim_web.service.base.ExampleService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExampleServlet", urlPatterns = {"/example"})
public class ExampleServlet extends HttpServlet {

    @Inject
    ExampleService exampleService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String greeting = exampleService.getGreeting();
        request.setAttribute("greeting", greeting);
        request.getRequestDispatcher(Path.INDEX_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
