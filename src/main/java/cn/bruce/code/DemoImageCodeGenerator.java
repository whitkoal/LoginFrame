package cn.bruce.code;

import cn.bruce.security.core.validate.code.ImageCode;
import cn.bruce.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        // 图形验证码生成器
        return null;
    }
}
