package cn.bruce.security.core.properties;

/**
 * 短信验证码的参数配置，包括验证码的验证码字数、有效时间、验证码生效路径
 * （多个路径用逗号隔开）
 */
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    // 短信验证码生效路径
    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
