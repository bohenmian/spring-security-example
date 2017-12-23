package cn.edu.swpu.cins.springsecurityexample.enums;

import cn.edu.swpu.cins.springsecurityexample.constant.SecurityContants;

public enum ValidateCodeType {
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityContants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityContants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    public abstract String getParamNameOnValidate();
}
