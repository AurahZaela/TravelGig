package com.synex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sentEmail(String email, String title, String message, String pdfPath, String filename){

        MimeMessagePreparator msgPreparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setTo(new InternetAddress(email));
                helper.setSubject(title);
//              helper.setText(message);
                helper.setText("Thank You For Booking With Us. Here are your booking details.");
                helper.addAttachment(filename, new ClassPathResource("/pdf/"+filename)); //ClassPath was looking for path at root folder
                
                //MimeBodyPart attache = new MimeBodyPart();
                
                if(pdfPath == null){
                    helper.addAttachment("Reservation_Information.pdf", new ClassPathResource(pdfPath));
                }     
            }
            
        };

        try{
            javaMailSender.send(msgPreparator);
        }catch(MailException ex){
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        
    }
}
