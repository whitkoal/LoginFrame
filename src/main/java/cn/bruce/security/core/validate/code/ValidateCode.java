package cn.bruce.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * 短信验证码
 */
public class ValidateCode {

    private String code;
    // 过期时间
    private LocalDateTime expireTime;


    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 验证码是否已经过期。
     *
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    @Override
    public String toString() {
        return "ValidateCode{" +
                "code='" + code + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
