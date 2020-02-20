package cn.bruce.security.core.validate.code.impl;

import cn.bruce.security.core.validate.code.ValidateCode;
import cn.bruce.security.core.validate.code.ValidateCodeGenerator;
import cn.bruce.security.core.validate.code.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现；
     * spring会自动查找 {@link ValidateCodeGenerator} 的实现类，放入Map中，key是实现类的名字
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        ValidateCode validateCode = generate(request);
        save(request.getRequest(), validateCode);
        send(request, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return validateCode
     */
    private ValidateCode generate(ServletWebRequest request) {
        String type = getProcessorType(request.getRequest());
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return validateCodeGenerator.generate(request.getRequest());
    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(HttpServletRequest request, ValidateCode validateCode) {
        System.out.println(validateCode.toString());
        request.getSession().setAttribute(SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, ValidateCode validateCode) throws Exception;

    /**
     * 根据请求的url获取校验码类型
     *
     * @param request
     * @return
     */
    private String getProcessorType(HttpServletRequest request) {
        String[] split = request.getRequestURI().split("/code/");
        return split[1];
    }
}
