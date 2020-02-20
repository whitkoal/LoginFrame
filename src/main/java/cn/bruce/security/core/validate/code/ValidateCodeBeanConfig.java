package cn.bruce.security.core.validate.code;

import cn.bruce.security.core.properties.SecurityProperties;
import cn.bruce.security.core.validate.code.image.ImageCodeGenerator;
import cn.bruce.security.core.validate.code.sms.DefaultSmsCodeSender;
import cn.bruce.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对图片验证码、短信验证码实现方式的配置类
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图片验证码实现方式配置
     * @return ValidateCodeGenerator
     * @ConditionalOnMissingBean(name = "imageCodeGenerator")注解的作用是：
     * 如果在Spring容器中已经有一个"imageCodeGenerator"Bean，就不再执行imageCodeGenerator()方法。
     * 如果没有就会继续执行imageCodeGenerator()方法生成"imageCodeGenerator"Bean。
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 短信验证码实现方式配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
