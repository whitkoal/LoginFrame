package cn.bruce.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 图片验证码验证失败异常
 */
public class validateCodeException extends AuthenticationException {

    public validateCodeException(String msg) {
        super(msg);
    }
}
