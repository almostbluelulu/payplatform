/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.dto;

import com.shentu.g3.facade.whitebroad.annotation.IsSignFile;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;

import java.io.Serializable;
import java.lang.reflect.Field;
/**
 * @ClassName: BasesSignDTO
 * @Description: BasesSignDTO
 * @author: dongxulu 签名方法基类
 * @date: 17/9/26 下午8:10
 * @version: 1.0.0
 */
public class BasesSignDTO implements Serializable{

    private static final long serialVersionUID = 1l;

    public String getSingString() throws WbSysException {
        StringBuilder sgin = null;
        try {
            sgin = new StringBuilder();
            Field[] files = this.getClass().getDeclaredFields();
            for(Field field:files){
//                if(field.getType().equals(long.class)){
//                    continue;
//                }
               boolean isSign = field.isAnnotationPresent(IsSignFile.class);
                if(!isSign){
                    continue;
                }
                field.setAccessible(true);
                sgin.append(field.getName());
                sgin.append("=");
                sgin.append(field.get(this)==null?"":field.get(this));
                sgin.append("&");
            }
        } catch (IllegalAccessException e) {
            throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION,e);
        }
        return  sgin.toString().substring(0,sgin.length()-1);
    }
}