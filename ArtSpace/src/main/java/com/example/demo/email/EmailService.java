package com.example.demo.email;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.demo.user.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final UserService userService;

    public EmailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    public String sendMail(EmailMessage emailMessage, String type) {
        String authNum = createCode();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if ("password".equals(type)) {
            userService.setTempPassword(emailMessage.getTo(), authNum);
        }

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(setContext(authNum, type), true);

            javaMailSender.send(mimeMessage);

            log.info("Success");

            return authNum;

        } catch (MessagingException e) {
            log.info("Fail", e);
            throw new RuntimeException(e);
        }
    }

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

    public String setContext(String code, String type) {
        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
    
    public String sendJoinMail(EmailMessage emailMessage) {
        String code = createCode(); // 인증 코드 생성
        emailMessage.setMessage(code); // 메시지에 인증 코드 설정
        emailMessage.setSubject("[ArtSpace] 회원가입 인증을 위한 인증 코드 발송");
        sendMail(emailMessage, "join_code_template"); // Thymeleaf 템플릿 이름 전달
        return code;
    }
}
