package cn.bruce.security.core.validate.code.image;

import cn.bruce.security.core.validate.code.ValidateCode;
import cn.bruce.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 图片验证码处理器
 */
@Component("imageCodeProcessor")
public class imageCodeProcessor extends AbstractValidateCodeProcessor {

    /**
     * 发送图形验证码，将其写到响应中
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        ImageCode imageCode = (ImageCode) validateCode;
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
