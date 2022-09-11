package com.example.MailSenderMS.controller;

import com.example.MailSenderMS.model.EmailDetails;
import com.example.MailSenderMS.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/sendMail")
    public String sendSimpleMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleEmail(details);
    }
}
