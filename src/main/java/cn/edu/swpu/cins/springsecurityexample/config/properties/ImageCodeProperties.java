package cn.edu.swpu.cins.springsecurityexample.config.properties;

public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    //默认配置
    private int width = 67;
    private int height = 23;
    //配置多个请求的url


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
