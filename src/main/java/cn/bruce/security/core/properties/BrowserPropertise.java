package cn.bruce.security.core.properties;

public class BrowserPropertise {

    // 默认登陆页面为 /signin.html
    private String loginPage = "/signin.html";

    // 登陆信息返回方式，默认为JSON
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
