package cn.bruce.security.core.validate.code;

import cn.bruce.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     *  @ConditionalOnMissingBean(name = "imageCodeGenerator")注解的作用是：
     *  如果在Spring容器中已经有一个"imageCodeGenerator"Bean，就不再执行imageCodeGenerator()方法。
     *  如果没有就会继续执行imageCodeGenerator()方法生成"imageCodeGenerator"Bean。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
}
