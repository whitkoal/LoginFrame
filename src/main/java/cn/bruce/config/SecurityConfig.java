package cn.bruce.config;

import cn.bruce.security.browser.authentication.MylibAuthenticationFailureHandler;
import cn.bruce.security.browser.authentication.MylibAuthenticationSuccessHandler;
import cn.bruce.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import cn.bruce.security.core.properties.SecurityProperties;
import cn.bruce.security.core.validate.code.SmsCodeFilter;
import cn.bruce.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 注入用户配置的登录页，该登录页不需要权限认证
    @Autowired
    private SecurityProperties securityProperties;

    // 注入登陆成功处理器，名字一样就能注入进来
    @Autowired
    private MylibAuthenticationSuccessHandler mylibAuthenticationSuccessHandler;

    // 注入登陆失败处理器
    @Autowired
    private MylibAuthenticationFailureHandler mylibAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    // 记住我功能的用户信息存储数据源
//    @Autowired
//    private DataSource dataSource;

    // 记住用户登录信息功能的配置
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setCreateTableOnStartup(true);
//        return tokenRepository;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 图形验证码设置
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(mylibAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        // 短信验证码设置
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(mylibAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)  //将短信验证码过滤器加到过滤器链上去 （加到UsernamePasswordAuthenticationFilter之前）
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)  // 将图形验证码过滤器加入到过滤器链上去
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form") // 设置登录请求提交的数据与 UsernamePasswordAuthenticationFilter 过滤器的映射
                .successHandler(mylibAuthenticationSuccessHandler) // 登陆成功处理器
                .failureHandler(mylibAuthenticationFailureHandler) // 登陆失败处理器
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/authentication/mobile", securityProperties.getBrowser().getLoginPage(), "/code/*").permitAll()
                .antMatchers("/publicResources/", "/signup", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .httpBasic();
    }
}
