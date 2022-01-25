package com.icia.project.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration: 설정정보를 스프링실행시 등록해줌.
@Configuration // 인터셉트를 적용하기 싫으면 해당 어노테이션 주석처리
public class webConfig implements WebMvcConfigurer {
    // 로그인여부에 따른 접속가능 페이지 구분
    @Override
    public void addInterceptors(InterceptorRegistry registry){ //체이닝메서드
        registry.addInterceptor(new LoginCheckInterceptor()) // 만든 LoginCheckInterceptor 클래스 내용을 넘김
                .order(1) // 해당 인터셉터가 적용되는 순서
                .addPathPatterns("/**") // 해당 프로젝트의 모든 주소에 대해 인터셉터를 적용
                .excludePathPatterns("/","/member/save","/member/login","/member/logout","/css/**"); // 그중에 이 주소는 제외, //비로그인도 볼수 있는 주소들
                // -> / 조심하자 무한 리다이렉트에 빠질 수 있다 ㅠㅠ
    }
}
