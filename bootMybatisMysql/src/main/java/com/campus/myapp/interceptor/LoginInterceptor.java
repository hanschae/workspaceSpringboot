package com.campus.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	// 컨트롤러가 호출되기 전에 인터셉터하는 메소드이다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 로그인 여부 확인
		HttpSession session = request.getSession();
		
		// session에서 로그인 정보를 구해온다.
		String logStatus = (String)session.getAttribute("logStatus"); // null, Y
		
		if(logStatus!=null&&logStatus.equals("Y")) {
			// 로그인 상태일때
			return true;
		}else {
			// 로그인 안된경우
			response.sendRedirect("/member/login");
		}
		return false;
		
	}
	
	// postHandle()
	// agterCompletion()
	
}
