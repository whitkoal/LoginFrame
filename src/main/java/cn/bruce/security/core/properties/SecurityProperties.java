package cn.bruce.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义配置，包括浏览器配置、验证码配置
 */
@ConfigurationProperties(prefix = "mylib.security")
public class SecurityProperties {
    // 浏览器配置
    private BrowserPropertise browser =  new BrowserPropertise();
    // 验证码配置
    private ValidateCodeProperties code = new ValidateCodeProperties();


    public BrowserPropertise getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserPropertise browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
