package cn.bruce.security.core.validate.code;

import cn.bruce.security.browser.authentication.MylibAuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码过滤器。继承了OncePerRequestFilter过滤器，保证每次请求只会经过一次过滤器。
 */
public class validateCodeFilter extends OncePerRequestFilter {

    private MylibAuthenticationFailureHandler mylibAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 验证请求是否合法，请求路径正确，而且必须是POST请求
        if (StringUtils.pathEquals("/authentication/form", request.getRequestURI())
                && request.getMethod().equals("post")) {
            try {
                // 用于校验图片验证码的方法。用ServletWebRequest 包装Request后作为入参
                validated(new ServletWebRequest(request));
            }catch (validateCodeException e){
                mylibAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validated(ServletWebRequest servletWebRequest) {
    }
}
