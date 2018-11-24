package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.MailMessage;

public interface MailService {
    
    boolean sendMail(String toEmail, MailMessage mailMessage);
    
}
