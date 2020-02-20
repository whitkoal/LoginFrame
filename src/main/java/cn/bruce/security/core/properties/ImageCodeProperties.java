package cn.bruce.security.core.properties;

import cn.bruce.security.core.validate.code.sms.SmsCodeSender;

/**
 * 图片验证码的参数配置，包括验证码的宽度、高度、验证码字数、有效时间、验证码生效路径
 * （多个路径用逗号隔开）
 */
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;
    private int height = 23;

    ImageCodeProperties(){
        this.setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
