/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.dto.ext;
/**
 * 类名称: UnionH5payParamDTO <br>
 * 类描述: <br>
 *  银联接入参数实体,如果需要其他参数,在此类添加,统一变更
 * @author: xxxx.xxx
 * @since: 17/11/17 下午3:13
 * @version: 1.0.0
 */

import java.io.Serializable;

/**
 * Created by dongxulu on 17/11/17.
 */
public class UnionH5payParamDTO implements Serializable {
    private static final long serialVersionUID = 4264036390571272643L;

    /**
     * 商户编号
     */
    private String merId;
    /**
     * 交易金额 单位分
     */
    private String txnAmt;
    /**
     * 接入类型
     * 0:商户直连接入
     * 1:收单机构接入
     * 2:平台商户接入
     */
    private String accessType;
    /**
     * 版本号
     */
    private String version;
    /**
     * 编码
     */
    private String encoding;
    /**
     * 签名方法
     */
    private String signMethod;
    /**
     * 交易类型
     *00:查询交易
     *01:消费
     *02:预授权
     *03:预授权完成
     *04:退货
     *05:圈存
     *11:代收
     *12:代付
     *13:账单支付 14:转账(保留) 21:批量交易
     *22:批量查询
     *31:消费撤销 32:预授权撤销 33:预授权完成撤销 71:余额查询 72:实名认证-建立绑定关系 73:账单查询 74:解除绑定关系 75:查询绑定关系 77:发送短信验证码交易 78:开通查询交易 79:开通交易
     *94:IC 卡脚本通知 95:查询更新加密公钥证书
     */
    private String txnType;
    /**
     * 交易子类型 01 自助消费
     */
    private String txnSubType;
    /**
     *业务类型
     * 000201:B2C 网关支付  000301:认证支付 2.0   000302:评级支付 000401:代付
     * 000501:代收 000601:账单支付 000801:跨行收单 000901:绑定支付 001001:订购 000202:B2B
     */
    private String bizType;
    /**
     * 渠道类型 05:语音 07:互联网 08:移动 16:数字机顶盒
     */
    private String channelType;
    /**
     *二级商户代码
     *商户类型为平台类商户接入时必须上送 5-15长度
     */
    private String subMerId;
    /**
     *二级商户名称
     *商户类型为平台类商户接入时必须上送
     *最长 40 个字节,不支持换行符等不可见字符
     */
    private String subMerName;
    /**
     *二级商 户简称
     *仅支持字母或汉字或其组合,最长 8 位,不支持换行符 等不可见字符。
     *商户类型为平台类商户接入时必须上送
     */
    private String subMerAbbr;
    /**
     *账号
     *交易账号。请求时使用加密公钥对交易账号加密,并做 Base64 编码后上送;
     *应答时如需返回,则使用签名私钥 进行解密。 前台交易可由银联页面采集,
     *也可由商户上送并返显。 如需锁定返显卡号,应通过保留域(reserved)上送卡 号锁定标识
     */
    private String accNo;
    /**
     * ****************
     * 卡号锁定标识 cardNumberLock
     * 取值为“1”时,表示锁定卡号, 样例如下:
     * reserved={ cardNumberLock=1} 否则不锁定
     * *****************
     * 客户ID customerId
     * 渠道商户采用全渠道前台跳转 模式进行还款、缴费、支付时,
     * 通过 ID 标识号(渠道商户 ID+ 客户 ID)记忆客户常用卡号功能
     */
    private String reserved = "{cardNumberLock=1}";
    /**
     *商户订 单号
     *商户订单号,不能含“-”或“_”
     */
    private String orderId;
    /**
     * 订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，
     * 必须取当前时间，否则会报txnTime无效
     */
    private String txnTime;
    /**
     *交易币种（境内商户一般是156 人民币）
     */
    private String currencyCode;
    /**
     *前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
     */
    private String frontUrl;
    /**
     *注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
     *    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，
     *      那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
     *    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d
     *      在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
     */
    private String backUrl;
    /**
     * 订单超时时间。
     * 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。
     * 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
     * 此时间建议取支付时的北京时间加15分钟。
     * 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
     */
    private String payTimeout;


    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getSubMerId() {
        return subMerId;
    }

    public void setSubMerId(String subMerId) {
        this.subMerId = subMerId;
    }

    public String getSubMerName() {
        return subMerName;
    }

    public void setSubMerName(String subMerName) {
        this.subMerName = subMerName;
    }

    public String getSubMerAbbr() {
        return subMerAbbr;
    }

    public void setSubMerAbbr(String subMerAbbr) {
        this.subMerAbbr = subMerAbbr;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getPayTimeout() {
        return payTimeout;
    }

    public void setPayTimeout(String payTimeout) {
        this.payTimeout = payTimeout;
    }
}