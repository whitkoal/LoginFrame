package cn.bruce.security.browser.authentication;


import cn.bruce.security.core.properties.LoginType;
import cn.bruce.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理器
 */
@Component("mylibAuthenticationSuccessHandler")
public class MylibAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 用来做JSON转换的 ObjectMapper
    @Autowired
    private ObjectMapper objectMapper;

    // 注入配置
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 登陆成功后会调用这个方法
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功！");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            // 如果配置的登陆返回值类型为JSON，就返回JSON信息
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            // 返回值类型不为JSON，就交给父类 (SpringSecurity的默认方法) 来处理，
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
