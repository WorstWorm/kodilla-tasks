package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    @Autowired
    private MailCreatorService mailCreatorService;
    private final JavaMailSender javaMailSender;

//    public void send(final Mail mail) {
//        log.info("Starting email preparation...");
//        try {
//            SimpleMailMessage mailMessage = createMailMessage(mail);
//            javaMailSender.send(mailMessage);
//            log.info("Email has been sent.");
//        } catch (MailException e) {
//            log.error("Failed to process email sending: " + e.getMessage(), e);
//        }
//    }

    public void send(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail));
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

//    private SimpleMailMessage createMailMessage(final Mail mail) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        Optional<String> cC = Optional.ofNullable(mail.getToCc());
//        if(cC.isPresent()) {
//            mailMessage.setCc(cC.get());
//        }
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());
//        return mailMessage;
//    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }
    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        return mailMessage;
    }
}
