package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.UserSexConstant;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.model.UserType;
import com.moommim.moommim_web.service.base.UserService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ส่งไป userview
    }

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//           รัย req menu
//        เช็ต req menu 
//                1.

//              Email : <input type="email" required name="email" /><br>
//            Password : <input type="password" required name="password" /><br>
//            FirstName : <input type="text" required name="firstname" /><br>
//            LastName : <input type="text" required name="lastname" /><br>
//            DOB : <input type="date"  name="dob" /><br>
//            Mobile : <input type="text" required name="mobile" /><br>
//            Gender : <input type="text" required name="gender" /><br>
        String action = request.getParameter("action");
        
        switch(action){
            case "create" :CreateUser(request, response) ;break;
            case "edit" :EditUser(request, response) ;break;
        
            default: sendRedirectToPage(ViewPath.ACTION_USER_VIEW, response);break;
        }


    }
    
    private void CreateUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");

        if (Util.isNotEmpty(email)
                && Util.isNotEmpty(password)
                && Util.isNotEmpty(firstname)
                && Util.isNotEmpty(lastname)
                && Util.isNotEmpty(mobile)
                && Util.isNotEmpty(gender)) {
            if (gender.equals(UserSexConstant.FEMALE) || gender.equals(UserSexConstant.MALE) || gender.equals(UserSexConstant.OTHER)) {

                UserAccount userAccount = new UserAccount();
                userAccount.setEmail(email);
                userAccount.setPassword(password);
                userAccount.setFirstName(firstname);
                userAccount.setLastName(lastname);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
                String formattedDate = dateFormat.format(new Date());
                try {
                    Date date = dateFormat.parse(dob);
                    userAccount.setDob(date);
                } catch (ParseException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    sendRedirectToPage(ViewPath.CREATE_USER_VIEW, response);
                }

                userAccount.setMobile(mobile);
                UserType type = new UserType(2);
                userAccount.setType(type);
                userAccount.setGender(gender);

                if (userService.createUser(userAccount)) {
                    sendToPage(ViewPath.ACTIVE_USER_VIEW, request, response);
                }
            }
        }
    
    }

    private void EditUser(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
