package com.campus.myapp;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.campus.myapp.interceptor.LoginInterceptor;

@Configuration
public class ServerConfigure implements WebMvcConfigurer {
	// 인터셉트가 처리될 매핑주소를 List컬렉션으로 작성한다.
	private static final List<String> URL_PATTERNS = Arrays.asList(
			"/member/memberEdit","/member/memberEditOk",
			
			"/board/boardForm","/board/boardForm","/bard/boardFormOk","/board/boardEdit",
			"/board/boardEditOk","/board/boardDel","/board/multiDel",
			
			"/data/dataWrite","/data/dataWriteOk",
			"/data/dataEditForm/*","/data/dataEditFormOk",
			"/data/dataDel"
			
			);
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(URL_PATTERNS);
	}
}
