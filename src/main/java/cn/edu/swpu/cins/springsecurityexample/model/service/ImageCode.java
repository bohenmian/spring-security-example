package cn.edu.swpu.cins.springsecurityexample.model.service;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime time;

    public ImageCode(BufferedImage image, String code, int time) {
        this.image = image;
        this.code = code;
        this.time = LocalDateTime.now().plusSeconds(time);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime time) {
        this.image = image;
        this.code = code;
        this.time = time;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
