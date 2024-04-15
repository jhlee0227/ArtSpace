package com.example.demo.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
 
    private JavaMailSender emailSender;
    private TemplateEngine templateEngine;
 
    public void sendMail() throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //템플릿에 전달할 데이터 설정
        HashMap<String, String> emailValues = new HashMap<>();
        emailValues.put("title", "제목");
        emailValues.put("text", "내용");

        Context context = new Context();
        emailValues.forEach((key, value)->{
            context.setVariable(key, value);
        });

        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process("mail/mail", context);
        helper.setText(html, true);

        //템플릿에 들어가는 이미지 cid로 삽입
        helper.addInline("image1", new ClassPathResource("img/logo.png"));

        // 참조자 설정
        helper.setCc("namug1105@gmail.com");

        // 첨부 파일 설정
        File file = new File("img/logo.png");
        FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), 
        		false, file.getName(), (int) file.length(), file.getParentFile());
        InputStream input = new FileInputStream(file);
        OutputStream os = fileItem.getOutputStream();
        IOUtils.copy(input, os);
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        helper.addAttachment(MimeUtility.encodeText(fileName, "UTF-8", "B"), 
        		new ByteArrayResource(IOUtils.toByteArray(multipartFile.getInputStream())));

        //메일 전송(setTo 파라미터에 문자열 리스트를 넘기면 한번에 여러명에게 전송 가능)
        helper.setTo("namug1105@gmail.com");
        emailSender.send(message);
    }
}