package cn.edu.swpu.cins.springsecurityexample.generate.impl;

import cn.edu.swpu.cins.springsecurityexample.generate.SmsCodeSenderGenerator;

public class SmsCodeSenderGeneratorImpl implements SmsCodeSenderGenerator {

    @Override
    public void send(String mobile, String code) {
        //TODO send code to mobile
        System.out.println("发送短信验证码");
    }
}
