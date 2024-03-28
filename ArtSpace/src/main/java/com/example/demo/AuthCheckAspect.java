package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

public class AuthCheckAspect {
	 
	  /**
	   * 고객의 로그인을 체크한다.
	   */
	  @Before("@annotation(com.delfood.aop.MemberLoginCheck)")
	  public void userLoginCheck(JoinPoint jp) throws Throwable {	    
	    HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
	    String memberId = SessionUtil.getLoginUserId(session);
	    
	    if (memberId == null) {
	      throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "NO_LOGIN") {};
	    }
	  }
}
