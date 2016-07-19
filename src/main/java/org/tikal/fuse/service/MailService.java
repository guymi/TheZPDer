package org.tikal.fuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;

/**
 * Created by guy on 7/19/16.
 */
@Service
public class MailService {

    @Autowired
    private MailSender mailSender;

    public void sendMail(String from, String to, String subject, String msg) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);
        mailSender.send(simpleMailMessage);
    }
}
