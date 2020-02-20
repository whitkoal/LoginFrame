package cn.bruce.security.core.validate.code.sms;

/**
 * 默认的短信验证码发送方式， 实现了SmsCodeSender。
 * 这里没有具体实现短信验证码的发送， 这里可以通过调用短信发送商的接口来发送短信验证码
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println(mobile + ": " + code);
    }
}
