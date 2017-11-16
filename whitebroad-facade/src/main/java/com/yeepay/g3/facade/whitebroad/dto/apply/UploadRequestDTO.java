package com.yeepay.g3.facade.whitebroad.dto.apply;

import com.yeepay.g3.facade.whitebroad.dto.BaseRequest;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.lang.reflect.Field;

/**
 * 文件上传入参
 *
 * @author hongyu.liu
 * @date 2017年9月28日 上午10:11:03
 */
public class UploadRequestDTO extends BaseRequest {

	private static final long serialVersionUID = -4904566853254916387L;

    /**
     * 身份证正面
     */
    private String idcardImg1;

    /**
     * 身份证反面
     */
    private String idcardImg2;

    /**
     * 结算银行卡正面
     */
    private String bankcardImg;

    /**
     * 手持身份证
     */
    private String handidcardImg;

    public String getIdcardImg1() {
        return idcardImg1;
    }

    public void setIdcardImg1(String idcardImg1) {
        this.idcardImg1 = idcardImg1;
    }

    public String getIdcardImg2() {
        return idcardImg2;
    }

    public void setIdcardImg2(String idcardImg2) {
        this.idcardImg2 = idcardImg2;
    }

    public String getBankcardImg() {
        return bankcardImg;
    }

    public void setBankcardImg(String bankcardImg) {
        this.bankcardImg = bankcardImg;
    }

    public String getHandidcardImg() {
        return handidcardImg;
    }

    public void setHandidcardImg(String handidcardImg) {
        this.handidcardImg = handidcardImg;
    }

    public String toString() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(
                this, ToStringStyle.SHORT_PREFIX_STYLE) {
            @Override
            protected boolean accept(Field field) {
                return !field.getName().equals("idcardImg1") && !field.getName().equals("idcardImg2")
                        && !field.getName().equals("bankcardImg") && !field.getName().equals("handidcardImg");
            }
        };
        return builder.toString();
    }
}
