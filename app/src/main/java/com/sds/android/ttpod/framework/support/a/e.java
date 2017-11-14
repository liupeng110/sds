package com.sds.android.ttpod.framework.support.a;

import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.ttpod.framework.R;
import com.tencent.connect.common.Constants;

/* PlayErrorTranslator */
public class e {
    public int a(int i) {
        if (i >= SecExceptionCode.SEC_ERROR_SIGNATRUE && i <= 855) {
            return R.string.play_error_code_600_855;
        }
        if (i >= 2001 && i <= 2017) {
            return R.string.play_error_code_2001_2017;
        }
        if (i == 905) {
            return R.string.play_error_code_905;
        }
        if (i >= 1000 && i <= 1255) {
            return R.string.play_error_code_1000_1255;
        }
        if (i >= 1300 && i <= 1558) {
            return R.string.play_error_code_1300_1558;
        }
        if (i == 1559) {
            return R.string.play_error_code_1559;
        }
        if (i == -5000) {
            return R.string.play_error_code_5000;
        }
        int i2 = R.string.play_error;
        switch (i) {
            case -60:
                return R.string.play_error_code_60;
            case -58:
                return R.string.play_error_code_58;
            case -57:
                return R.string.play_error_code_57;
            case -56:
                return R.string.play_error_code_56;
            case -55:
                return R.string.play_error_code_55;
            case -54:
                return R.string.play_error_code_54;
            case -36:
                break;
            case -34:
                i2 = R.string.play_error_code_34;
                break;
            case -23:
                return R.string.play_error_code_23;
            case -21:
                return R.string.play_error_code_21;
            case -18:
                return R.string.play_error_code_18;
            case -15:
                return R.string.play_error_code_15;
            case Constants.ERROR_NO_SDCARD /*-12*/:
                return R.string.play_error_code_12;
            case -6:
                return R.string.play_error_code_6;
            default:
                return i2;
        }
        return R.string.play_error_code_36;
    }
}
