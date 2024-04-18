package com.example.demo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.demo.email.EmailMessage;
import com.example.demo.reservation.dto.ReservationDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailUtil {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	SpringTemplateEngine springTemplateEngine;
	
	
	// 메일 보내기
	public String sendMail(EmailMessage emailMessage, String templateURL, String authNum) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

//        if ("password".equals(type)) {
//            userService.setTempPassword(emailMessage.getTo(), authNum);
//        }

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(setContext(authNum, templateURL), true);

            javaMailSender.send(mimeMessage);

//            log.info("Success");

            return authNum;

        } catch (MessagingException e) {
//            log.info("Fail", e);
            throw new RuntimeException(e);
        }
    }
	
	// 메일 
	public void sendMail(EmailMessage emailMessage, String templateURL) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(setObjContext(emailMessage.getMessage(), templateURL), true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

	// 인증 코드 생성
    public String createCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) (random.nextInt(26) + 97)); break;
                case 1: key.append((char) (random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(10));
            }
        }
        return key.toString();
    }

    // 회원가입용
    public String setContext(String code, String templateURL) {
        Context context = new Context();
        context.setVariable("code", code);
        return springTemplateEngine.process(templateURL, context);
    }
    
    // 메일 템플릿 안에 내용 타임리프 넣기
    public String setObjContext(Object obj, String templateURL) {
        Context context = new Context();
        context.setVariable("obj", obj);
        return springTemplateEngine.process(templateURL, context);
    }
    
    
    
    public String sendJoinMail(EmailMessage emailMessage) {
        String code = createCode(); // 인증 코드 생성
        emailMessage.setMessage(code); // 메시지에 인증 코드 설정
        emailMessage.setSubject("[ArtSpace] 회원가입 인증을 위한 인증 코드 발송");
        sendMail(emailMessage, "html/email_templates/email_join", code); // Thymeleaf 템플릿 이름 전달
        return code;
    }

	public void sendReservationSCEmail(EmailMessage emailMessage, ReservationDTO reservation) {
        emailMessage.setMessage(reservation); // 메시지에 인증 코드 설정
        
        emailMessage.setSubject("[ArtSpace] 예약 완료 메세지");
        sendMail(emailMessage, "html/email_templates/email_reservationSC"); // Thymeleaf 템플릿 이름 전달        
	}
	
}
