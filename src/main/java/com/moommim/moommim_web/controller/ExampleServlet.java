package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.Ads;
import com.moommim.moommim_web.service.base.ExampleService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExampleServlet", urlPatterns = {"/example"})
public class ExampleServlet extends BaseController {

    @Inject
    private ExampleService exampleService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Ads> ads = exampleService.getAllAds();
            Ads ad = exampleService.getAdsById(2);
            if (Util.isNotEmpty(ad)) {
//                System.out.println("Ads Before Update: " + ad.getName());
//                ad.setName("Lazada.co.th");
//                ad = exampleService.updateAds(ad);
//                System.out.println("Ads After Update: " + ad.getName());
                for (Ads ad1 : ads) {
                    System.out.println("Ads List: " + ad1.getName());
                }
            }
        } catch (NoResultException ex) {
            System.out.println("Is No Result");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
