package cn.bruce.security.core.validate.code.image;

import cn.bruce.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;

/**
 * 图片验证码
 */
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * 验证码是否已经过期。
     *
     * @return
     */
    public boolean isExpired() {
        return super.isExpired();
    }
}
