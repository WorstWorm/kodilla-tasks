package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail
{
    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;

    public Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.toCc = null;
        this.subject = subject;
        this.message = message;
    }
}
