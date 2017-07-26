package com.gosun.isap.warn.impl.alert.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>创建时间：2017-6-7 16:43</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class CommonUtil {
    /**
     * @param phoneNumber 手机号
     * @return 是否有效
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if(phoneNumber == null){
            return false;
        }
        Pattern pattern = Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,1,6,7,8]))\\d{8}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
