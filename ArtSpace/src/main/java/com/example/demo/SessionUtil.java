package com.example.demo;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	  private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
	  private static final String LOGIN_COMPANY_ID = "LOGIN_COMPANY_ID";
	  private static final String LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID";
	  
	  
	  // 인스턴스화 방지
	  private SessionUtil() {}

	  //로그인한 고객의 아이디를 세션에서 꺼낸다.
	  public static String getLoginUserId(HttpSession session) {
	    return (String) session.getAttribute(LOGIN_USER_ID);
	  }
	  

	  //로그인한 고객의 아이디를 세션에 담는다.
	  public static void setLoginUserId(HttpSession session, String id) {
	    session.setAttribute(LOGIN_USER_ID, id);
	  }
	  
	  
	  
}
