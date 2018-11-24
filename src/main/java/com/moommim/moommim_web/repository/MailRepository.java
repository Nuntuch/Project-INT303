package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.config.App;
import com.moommim.moommim_web.model.MailMessage;
import java.util.logging.Logger;
import org.simplejavamail.MailException;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class MailRepository {

    private static final Logger LOGGER = Logger.getGlobal();

    public boolean send(String fromEmail, String fromEmailPassword, String toEmail, MailMessage mailMessage) {
        boolean sendResult = false;
        try {
            Email email = EmailBuilder.startingBlank()
                    .from(fromEmail)
                    .to(toEmail)
                    .withSubject(mailMessage.getSubject())
                    .withHTMLText(mailMessage.getContent())
                    .buildEmail();
            MailerBuilder
                    .withSMTPServer(App.SMTP_HOST, Integer.parseInt(App.SMTP_PORT), fromEmail, fromEmailPassword)
                    .buildMailer()
                    .sendMail(email);
            sendResult = true;
            LOGGER.info("Send mail is successfully");
        } catch (MailException e) {
            LOGGER.severe(e.getMessage());
            sendResult = false;
        }
        return sendResult;
    }

}
