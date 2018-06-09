package cn.edu.swpu.cins.springsecurityexample.config.properties;

/**
 * 通过继承SmsCodeProperties,添加一些图形验证码的属性
 */
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    //默认配置,如果在配置文件中有配置,则默认配置失效
    private int width = 67;
    private int height = 23;

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
