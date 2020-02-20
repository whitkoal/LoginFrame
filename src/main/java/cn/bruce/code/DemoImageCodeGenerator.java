package cn.bruce.code;

import cn.bruce.security.core.properties.SecurityProperties;
import cn.bruce.security.core.validate.code.image.ImageCode;
import cn.bruce.security.core.validate.code.image.ImageCodeGenerator;
import cn.bruce.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 继承{@link cn.bruce.security.core.validate.code.ValidateCodeGenerator}并生成名为imageCodeGenerator的Bean可覆盖原图形验证码生成器
 */
@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(HttpServletRequest request) {
        // 图形验证码生成器
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator.generate(request);
    }
}
