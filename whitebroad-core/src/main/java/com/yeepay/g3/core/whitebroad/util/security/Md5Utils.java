/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.util.security;
/**
 * 类名称: Md5Utils <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/27 下午3:17
 * @version: 1.0.0
 */

import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dongxulu on 17/9/27.
 */
public class Md5Utils {

    private static final char[] hexChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String encoderHmacSha256(String data, String key) {
        try {
            byte[] dataByte = data.getBytes();
            byte[] keyByte = key.getBytes();

            SecretKeySpec signingKey = new SecretKeySpec(keyByte, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(dataByte));
        } catch (NoSuchAlgorithmException ex) {
            throw new WbSysException(ErrorCode.MD5UTILS_EXCEPTION,ex);
        } catch (InvalidKeyException ex) {
            throw new WbSysException(ErrorCode.MD5UTILS_EXCEPTION,ex);
        } catch (Exception ex) {
            throw new WbSysException(ErrorCode.MD5UTILS_EXCEPTION,ex);
        }
    }

    public static String byte2hex(byte[] b) {
        return byte2hex(b, '\u0000');
    }

    public static String byte2hex(byte[] b, char delimeter) {
        StringBuffer sb = new StringBuffer();

        for(int n = 0; n < b.length; ++n) {
            byte2hex(b[n], sb);
            if(n < b.length - 1 && delimeter != 0) {
                sb.append(delimeter);
            }
        }

        return sb.toString().toLowerCase();
    }

    private static void byte2hex(byte b, StringBuffer buf) {
        int high = (b & 240) >> 4;
        int low = b & 15;
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }
}