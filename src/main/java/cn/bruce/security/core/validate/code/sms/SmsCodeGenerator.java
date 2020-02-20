package cn.bruce.security.core.validate.code.sms;

import cn.bruce.security.core.properties.SecurityProperties;
import cn.bruce.security.core.validate.code.ValidateCode;
import cn.bruce.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * 短信验证码生成器，继承与验证码生成器
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 生成短信验证码。
     *
     * @param request
     * @return ImageCode
     */
    public ValidateCode generate(HttpServletRequest request) {
        // 生成随机数
        Random random = new Random();
        String code = "";
        for (int i = 0; i < securityProperties.getCode().getSms().getLength(); i++) {
            code += random.nextInt(10);
        }
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
