package com.example.AccountMS.model;

import lombok.Data;

@Data
public class MailInfo {
    private String recipient;
    private String subject;
    private String msgBody;
}
