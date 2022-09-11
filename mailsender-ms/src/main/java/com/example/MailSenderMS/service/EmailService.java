package com.example.MailSenderMS.service;

import com.example.MailSenderMS.model.EmailDetails;

public interface EmailService {
    String sendSimpleEmail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
