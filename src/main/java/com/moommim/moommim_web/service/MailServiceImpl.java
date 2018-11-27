package com.moommim.moommim_web.service;

import com.moommim.moommim_web.config.App;
import com.moommim.moommim_web.model.MailMessage;
import com.moommim.moommim_web.repository.MailRepository;
import com.moommim.moommim_web.service.base.MailService;
import javax.inject.Inject;

public class MailServiceImpl implements MailService {

    @Inject
    MailRepository mailRepository;

    @Override
    public boolean sendMail(String toEmail, MailMessage mailMessage) {
        return mailRepository.send(App.EMAIL_ADDRESS, App.EMAIL_PASSWORD, toEmail, mailMessage);
    }

}
