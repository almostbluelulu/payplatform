package com.shentu.g3.facade.whitebroad.dto.apply;

import com.shentu.g3.facade.whitebroad.dto.BaseResponse;

/**
 * 文件上传出参
 *
 * @author hongyu.liu
 * @date 2017年9月28日 上午10:11:58
 */
public class UploadResponseDTO extends BaseResponse {

	private static final long serialVersionUID = 9142603754961864528L;

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
}
