package cn.edu.swpu.cins.springsecurityexample.config.properties;


public class ValidateCodeProperties {

    ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
