package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.model.controller.UserAccountJpaController;
import com.moommim.moommim_web.service.base.AuthenticationService;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import javax.websocket.Session;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    @PersistenceUnit(unitName = "com.moommim_moommim_web_war_1.0-SNAPSHOTPU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

    private UserAccountJpaController accountJpaController = new UserAccountJpaController(utx, emf);

    @Override
    public UserAccount login(String username, String password) {

        if (username != null && username.trim().length() > 0
                && password != null && password.trim().length() > 0) {

            List<UserAccount> userAccountList = accountJpaController.findUserAccountEntities();

            for (UserAccount userAccount : userAccountList) {
                if (userAccount.getEmail().equals(username)) {
                    if (userAccount.getPassword().equals(password)) {
                        return userAccount;
                    }

                }
            }
        }
        return null;

    }

    @Override
    public boolean logout(HttpSession session) {
        try {
            session.invalidate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean isLogin(HttpSession session) {

        if (session != null) {
            if (session.getAttribute("UserAccountObj") != null) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean ForgoutPassword(String username) {

        if (username != null && username.trim().length() > 0) {

            List<UserAccount> userAccountList = accountJpaController.findUserAccountEntities();

            for (UserAccount userAccount : userAccountList) {
                if (userAccount.getEmail().equals(username)) {

                    try {
                        //คำสั่งส่งEmail
                        class SendMail {

                            public int send(String to, String sub,
                                    String msg) {
                                String username = "test@gmail.com";
                                String password = "555";

                                Properties props = new Properties();

                                props.put("mail.smtp.host", "smtp.gmail.com");
                                //below mentioned mail.smtp.port is optional
                                props.put("mail.smtp.port", "587");
                                props.put("mail.smtp.auth", "true");
                                props.put("mail.smtp.starttls.enable", "true");

                                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(username, password);
                                    }
                                });

                                try {

                                    MimeMessage message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress(username, "Heh-Maybe-You-Forgot-To-Change-Header"));
                                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                                    message.setSubject(sub);
                                    message.setContent(msg, "text/html; charset=utf-8");

                                    Transport.send(message);
                                    return 0;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return -1;
                                }
                            }
                        }

                        return true;
                    } catch (Exception e) {
                        return false;
                    }

                }
            }
        }

        class EmailMsgManager {

            private String msg;

            public EmailMsgManager() {
            }

            public String regisSuccess(String username) {
                msg = "HTML Mail Code";
                return msg;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }

        return false;

    }

}
