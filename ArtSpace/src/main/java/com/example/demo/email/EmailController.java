package com.example.demo.email;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/send-mail")
@Controller
public class EmailController {
	
	private final EmailService emailService;
	
	public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
	
	// 임시 비밀번호 발급
	@PostMapping("/password")
	public ResponseEntity<Void> sendPasswordMail(@RequestBody EmailPostDto emailPostDto) {
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setTo(emailPostDto.getEmail());
		emailMessage.setSubject("[ArtSpace] 임시 비밀번호 발급");
		
		emailService.sendMail(emailMessage, "password");
		
		return ResponseEntity.ok().build();
	}
	
	// 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
	@PostMapping("/email")
	public ResponseEntity<EmailResponseDto> sendJoinMail(@RequestBody EmailPostDto emailPostDto) {
	    EmailMessage emailMessage = new EmailMessage();
	    emailMessage.setTo(emailPostDto.getEmail());

	    String code = emailService.sendJoinMail(emailMessage); // 인증 코드 생성 및 메일 전송

	    EmailResponseDto emailResponseDto = new EmailResponseDto();
	    emailResponseDto.setCode(code);

	    return ResponseEntity.ok(emailResponseDto);
	}

}
