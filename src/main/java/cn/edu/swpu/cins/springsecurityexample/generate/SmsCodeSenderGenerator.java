package cn.edu.swpu.cins.springsecurityexample.generate;

public interface SmsCodeSenderGenerator {

    void send(String mobile, String code);
}
