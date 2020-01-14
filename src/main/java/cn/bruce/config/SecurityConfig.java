package cn.bruce.config;

import cn.bruce.security.browser.MyPasswordEncoder;
import cn.bruce.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 注入用户配置的登录页，该登录页不需要权限认证
    @Autowired
    private SecurityProperties securityProperties;

    // 注入登陆成功处理器，名字一样就能注入进来
    @Autowired
    private AuthenticationSuccessHandler mylibAuthenticationSuccessHandler;

    // 注入登陆失败处理器
    @Autowired
    private AuthenticationFailureHandler mylibAuthenticationFailureHandler;

    public void configureClobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
        auth
                .inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("admin").password("123456").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form") // 设置登录请求提交的数据与 UsernamePasswordAuthenticationFilter 过滤器的映射
                .successHandler(mylibAuthenticationSuccessHandler) // 登陆成功处理器
                .failureHandler(mylibAuthenticationFailureHandler) // 登陆失败处理器
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage(), "/code/image").permitAll()
                .antMatchers("/publicResources/", "/signup", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .httpBasic();
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                //.logoutSuccessHandler(logoutSu)
                .invalidateHttpSession(true)
                // .addLogoutHandler(LogoutHandler)
                .and();
    }
}
