package cn.bruce.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mylib.security")
public class SecurityProperties {
    private BrowserPropertise browser =  new BrowserPropertise();

    public BrowserPropertise getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserPropertise browser) {
        this.browser = browser;
    }
}
