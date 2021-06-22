package me.silvernine.tutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	  @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/h2-console/**" // h2-console/ 하위 모든 요청과
                        ,"/favicon.ico"  // 파비콘 모두 무시하도록 설정
                        ,"/error"
                );
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests() // httpServletRequest를 사용하는 요청들에 대한 접근 제한 하겠다.
				.antMatchers("/api/hello").permitAll() // /api/hello에 대한 요청은 인증없이 접근을 허용하겠다.
				.anyRequest().authenticated(); // 나머지 요청들에 대해서는 모두 인증을 받아야 한다.
	}
}
