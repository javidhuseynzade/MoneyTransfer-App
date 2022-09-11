package com.example.MailSenderMS.model;

import lombok.Data;

@Data
public class MailInfo {
    private String recipient;
    private String subject;
    private String msgBody;
}
