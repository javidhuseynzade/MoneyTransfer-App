package com.example.MailSenderMS.service;

import com.example.MailSenderMS.model.EmailDetails;
import com.example.MailSenderMS.model.MailInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @RabbitListener(queues = "mail-sender-queue")
    public void receiveMessage(MailInfo mailInfo) {
        EmailDetails details = new EmailDetails();
        details.setRecipient(mailInfo.getRecipient());
        details.setSubject(mailInfo.getSubject());
        details.setMsgBody(mailInfo.getMsgBody());
        sendSimpleEmail(details);
    }

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;
    @Override
    public String sendSimpleEmail(EmailDetails details) {
        /*try {*/
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Message Sent Successfully...";
        /*} catch (Exception e) {
            return "Error occurred while sending mail";
        }*/
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return null;
    }
}
