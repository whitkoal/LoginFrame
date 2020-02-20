package cn.bruce.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {
    /**
     * 生成图片验证码。
     *
     * @param request
     * @return
     */
    public ValidateCode generate(HttpServletRequest request);
}
