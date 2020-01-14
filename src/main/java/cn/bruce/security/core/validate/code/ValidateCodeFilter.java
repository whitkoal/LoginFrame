package cn.bruce.security.core.validate.code;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码过滤器。继承了OncePerRequestFilter过滤器，保证每次请求只会经过一次过滤器。
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 验证请求是否合法，请求路径正确，而且必须是POST请求
        if (StringUtils.pathEquals("/authentication/form", request.getRequestURI())
                && request.getMethod().equals("post")) {
            try {
                // 用于校验图片验证码的方法。
                validated(request);
            } catch (validateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 用于校验图片验证码的方法。
     *
     * @param request
     */
    private void validated(HttpServletRequest request) throws ServletRequestBindingException {
        System.out.println("进入图片验证");
        ImageCode codeInSession = (ImageCode) request.getSession().getAttribute(validateCodeController.SESSION_KEY);
        System.out.println(codeInSession.toString());
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");

        if (codeInRequest.isBlank()) {
            throw new validateCodeException("验证码的值不能为空！");
        }

        if (codeInSession == null) {
            throw new validateCodeException("验证码不存在！");
        }

        if (codeInSession.isExpired()) {
            // Session过期，清除Session。
            request.getSession().removeAttribute(validateCodeController.SESSION_KEY);
            System.out.println("Session是否移除：--" + request.getSession().getAttribute(validateCodeController.SESSION_KEY) + "————");
            throw new validateCodeException("验证码已过期！");
        }

        if (!codeInRequest.equals(codeInSession.getCode())) {
            throw new validateCodeException("验证码不匹配！");
        }
        // 如果以上判断都通过，则说明验证码验证成功，将Session清除。
        request.getSession().removeAttribute(validateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
