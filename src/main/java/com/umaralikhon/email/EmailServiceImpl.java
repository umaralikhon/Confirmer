package com.umaralikhon.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String code) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("umaralikhon@gmail.com");
            messageHelper.setTo(to);
            messageHelper.setSubject("Confirm code");
            messageHelper.setText("Your confirm code: " + code + "\n " +
                    "Do not tell this code to anyone, even bank employees!");

            mailSender.send(messageHelper.getMimeMessage());
        }catch(MessagingException ex){
            ex.getMessage();
        }
    }
}
