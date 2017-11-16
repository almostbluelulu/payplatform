package com.yeepay.g3.core.whitebroad.util;

/**
 * Description: 常量
 * Author: jiawen.huang
 * Date: 2017/9/18
 * Time: 15:29
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class Constant {

	/**
	 * 是否生产环境
	 */
	public static final boolean IS_PRODUCTION_ENVI = false;

	/**
	 * DB_AES_KEY
	 */
	public static final String DB_AES_KEY = "d2hpdGVicm9hZeu3";

	/**
	 * STR_AES_KEY
	 */
	public static final String STR_AES_KEY = "U1RSX0FFU19LRV2k";

	/**
	 * PW_AES_KEY
	 */
	public static final String PW_AES_KEY = "UFdfQUV1TX02tFWQ";

	/**
	 * 极光标识
	 */
	public static final String WB_JPUSH_APP_KEY = "612b7b112c83dbbe57fb2a32";

	/**
	 * 极光密钥
	 */
	public static final String WB_JPUSH_APP_SECRET = "0f8152bfcba3da6a53457eb5";

	/**
	 * iOS推送参数
	 */
    public static final boolean WB_APNS_PRODUCTION = false;

	/**
	 * YOP应用名称
	 */
//	public static final String YOP_APP_KEY = "whitebroad";
//	public static final String YOP_APP_KEY = "OPR:10040040565";//QA
    public static final String YOP_APP_KEY = "OPR:10014929805";

	/**
	 * YOP应用密钥
	 */
//	public static final String YOP_APP_AES_SECRET = "mZ4eOMrysdSW6knUtJo5ZA==";//不用了，这是whitebroad的，不支持交易入网
//	public static final String YOP_APP_AES_SECRET = "Xb+OM+YreSAGlbyg6Oup+w==";//QA
    public static final String YOP_APP_AES_SECRET = "2EYArI6lAJHC2MUW9JjYHg==";

	/**
     * QA-YOP-RSA应用密钥
     */
//	public static final String YOP_APP_RSA_SECRET = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDEyNSgFM3SysRCM8j8tVKYRnllhTYDtxCsb65ZR1iRo5lIkTqDA/+oQLLNgNzQOfRE80qRfqawQY030w5GfFRTMUbHxy4ydrZjBim19A3w5JvOUWwsGAoVqXJ2jvc7KpE5kZlA+Yq3WACPYLHnmzmSI9+yPo5yTF9LxhbmSGmzd0o9u9+z6UIncMIOjEF+t2hEH7I6Nh5b0oA9lTCT4Lif3kJDbhS+KYRqDUCncmydJ0AvWWtUlkf7H/5i1d+uLPFuOD46FbL0q8GxvbG/F4LVAqEm2AmtDxkD9lv6cOyizk7g44FTzTsvTVuYNmcY8uIXM//u3SQzZRrGH+R5gLY3AgMBAAECggEBAL7EZoQNxv6bfSkkpKp/aWOfpl4JhrSRSsOvvNhl9vGxJI8Z4xxRyw0kM3JPg0EFfc0+AcMMgbGKyskcfNFOBD1mHtTbPejD9soGlncC8mTeakDX5ELWxyf6zbErpXchLAogIO7BO33D/T8BoVYCwAvxZRGZ3WPmr9d4YIi8GGRWAjeJ26BAGvuZfyqgvvhN5ls1he3fGnRN5YdBzNBMG3sHIRaGX6L3swaQNtZWEZ6dG4Wj4y1cGP27n2jm7XqI5EJbVVzB0qpOVX4lLAUxM2W0c84HB8jqcB6CwQcMIxu+r8Gfjejt8sCN1O4T4zc224VjkflkaaLjb16je5U4I5kCgYEA9/WYfCGtyM9kDvOZs/kLghc7O/jP5rItZZc4a6G3+dcg5yLLJ1vo/2BmzWhEgVi87Gi/SVaCexSuTJNQx1trGUCarwYZ/6LJD3PMH8kKvdEZ6q8Uwr+Io7oXuakVMTbIWmKwS9ZjuNhLLkTwEBlNxkpMdZacsHAFjrCxWzo20EMCgYEAyyppvNSBRj6Hi4IA+cWXJSFbUyAj9e3s/1NrevpRqzNhHlNAfgvHmvwbPaQ1fY1L1hJRIgHy0HV1C+TTdhkBnl4K3sFTaSmH9HaYJy0t5JcNIvIJIawVMxf/jl55LrEJING1SLErnmorhErQUk3Pn7bHVPm3ZmiVG+elcqrrTP0CgYEA5aHPrp2uCHtwKZyVqqOocbi8BaU+PGKkQiISgTMKY1lEwmlphLDMjbykV/QJBVS92Z6EEBIK0ptrc8U2GPnjrEY1OoPHDgSeeS3/SyxvQyzj+IBeFKE2lJIhqLIayzH1yCb/J6yLO8MrlNUJEY+thEaImpl50LhdnTCrf1XhL2sCgYAXUXzlYXac+1xPVNKL2HPqnROATPYTqgVDoO17JfPttG33LxL95Jg6X6k4v/oruOX1YhDR2GtoJbMTdGYY7fHTnXOd8Y6DSXMDSgFYrcTQsc2eLy2BIeElBiIc0xcRltw3Whp2h288NW9dNcEHUUr9R9tFE4xLQsGqfEHvEVMEeQKBgGLKlJpoF8jp8M21lgGJvuWZwSabygyiU1y8Ez2DDtlGhXYZ3EitWN6NcnVp0WJAKLv5uBM9JF5DgBapYk2r4M6hN7hSAhuWnrHAcEtY5MSJqDN7qXkb//iqb1H9YeZeurL0k1za9UThbePHP6SHgX5rUyKAd6z5njGNageieY2e";

    /**
     * 生产-YOP-RSA应用密钥
     */
    public static final String YOP_APP_RSA_SECRET = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDCC2oVe6OYd8ZtuhW9AN8wV9bat5wz3rva5H8iPAv99VQkORANnh6l+a7RNVfN9w+Yii6UeavhSsulgicDUngJdCHaPIsuXRWt26ejSsLeHmxXnWPG2AObZcnyYzUzwZ4MiAWJ6RcRrF7BZGpAPkBGK0kLBeZ9e8Ko8SgRUXzVHmPjg8oF5vV0xMNDj92X0oZBVfzt0rOSqlGVWWgRkgIBz6CZKiy9pmLnKOnpG5qOOdiTdth+DsAR7ABK4lzkPeesAsR1VzP4EqW/TKC64YKhMA3N1ovfMC9EpQ2oCPwvairAsQcB/pvXxHBXttF/BTrTw/Ks9tkh2QMRBvZGHpfAgMBAAECggEABr/1GibTEyKXi4uQjGolg9eyQdNPgiAuBQdVjdzAAriRlITiPSyRKD+K8zqogy8teUk1L+PoLkJ95vhzmRZWJ+XKyC7vyr4C8DSizigXf4/FNQ3YoHaYjCW5E6OeTZgcjTSH0pxYKyi5G809o6cZLKVIxgQ/cv7oQXQOPPNUlyQ/aBl1c1cSDAWbyX7BDduqZmk5BPnyud9vtEOuKAQqFwPfy3/ZfkibilUYcvtNqRSUl/7VinZeAisSXPbKre2qk5ll/YXeavxkBZxdq6/JS5O4ivBtrQy9Fnil+7hBe6Qfw4Lt7Fv5NdObJIVwzq7cMTHGxnUaf3MNRpdkHvJsgQKBgQDrPt7vI5BuMvzM6wILXnxQ76quYPFE9nJ1glYPCpCAirKP5kAEjmH/2mJ4IqTi2uT5pgoPb0zGspL7R1tsJcSgGa98qEgyeG1n+6C9M2a+vmht8VTj0nrZeIIigQH2dF16K8c87H1jgg0N5VrjG+pRKG23dQ0rX4O0B+3MoHUN3QKBgQCOl5hCO2OVvillvvk0Wabll3ytWYZZRN/4COWtDaXY10RkpeBRyDZvAUE9Gyi/ZegfvTfZzV5gPnVFtXqbIEY8u0xD4MQSAuncY4V16cv70cvu4u3xGEZKgzgk8TOfPNxInCWUles6lP451x5B3HIAa63Ii1j3Qd0ceuI8iqT7awKBgQCh8M7Q+r+DTPBANItcvjeAE+yATFXqrmjOweFyS0h8ZH5VlyB8wnNuCKz+nIK7dApqXUXRqEHHCskp1850nW9E80md286Ph91w1oSpmkfhiPwkqxxQFOXi7RVQoVRzj1mGL7rhEr+ij7Vi2n99lgrwwY792sMtF3x3o3mtAsxxtQKBgAf5YFFr4tDP9p6zBFqyHMxAIX/MPuAlIuVLEhUQa1LqDvAV+qp4KNsiVdSl/Sxe9ZE40rPCcWGufH5ufLHKJ0NkMgqlujFLqmphwmfqsDaf7+inFilicyPdnLksJ/fivmrtGIjrrWD0ThdL+WwzeMifPPO3Hz2MmGHsWVSLaFiLAoGBANL7mp+y+J9Olx9LPjR14lanOg4PhnhIJ/CQt41WWgkEbSXfign0LaYwJQ2ly6y8KoaVPN/VeICTQ9RXsvIAwUmy0YB4hvRS6kfsdsP+9MWMooecsnsz+fUgY+Ff6pJL3dhnr0cPqiB0J0xH2gMD80i9QFUfaWAmLD7KvB1y3XA4";


    /**
     * YOP-qa-系统商公钥
     */
	public static final String YOP_APP_RSA_PUBKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxMjUoBTN0srEQjPI/LVSmEZ5ZYU2A7cQrG+uWUdYkaOZSJE6gwP/qECyzYDc0Dn0RPNKkX6msEGNN9MORnxUUzFGx8cuMna2YwYptfQN8OSbzlFsLBgKFalydo73OyqROZGZQPmKt1gAj2Cx55s5kiPfsj6OckxfS8YW5khps3dKPbvfs+lCJ3DCDoxBfrdoRB+yOjYeW9KAPZUwk+C4n95CQ24UvimEag1Ap3JsnSdAL1lrVJZH+x/+YtXfrizxbjg+OhWy9KvBsb2xvxeC1QKhJtgJrQ8ZA/Zb+nDsos5O4OOBU807L01bmDZnGPLiFzP/7t0kM2Uaxh/keYC2NwIDAQAB";

    /**
     * YOP对接host
	 */
//	public static final String YOP_HOST = "http://10.151.30.80:18064/yop-center";
    public static final String YOP_HOST = "https://open.yeepay.com/yop-center";

	/**
	 *收银台标准支付地址 生产 https://cash.yeepay.com/cashier/std
	 * QA:http://10.151.30.8:8008/nc-cashier-wap/cashier/std
	 */
//	public static final String CASHIER_HOST = "http://10.151.30.8:8008/nc-cashier-wap/cashier/std";

    /**
     * 收银台标准支付地址 生产 https://cash.yeepay.com/cashier/std
     * QA:http://10.151.30.8:8008/nc-cashier-wap/cashier/std
     */
    public static final String CASHIER_HOST = "https://cash.yeepay.com/cashier/std";

	/**
	 * 验证码有效时间(s)
	 */
	public static final Integer SMS_CODE_EFFECT_INTERVAL = 300;

	/**
	 * 用户token刷新 续期时间/天
	 */
	public static final Integer TOKEN_KEEPALIVE_DAYS = 3;

	/**
	 * 请求yop成功返回状态码
	 */
	public static final String REQUEST_YOP_SUCCESS_CODE = "REG00000";

	/**
     * qa接收yop回调地址
     */
//    public static final String RECEIVE_APPLY_CALLBACK_ADDRESS_FROM_YOP = "http://10.151.31.89:8090/whitebroad-front/yop/open";

    /**
     * 生产接收yop回调地址
     */
    public static final String RECEIVE_APPLY_CALLBACK_ADDRESS_FROM_YOP = "https://wb.kqzmy.com/whitebroad-front/yop/open";

	/**
	 * 白板在易宝的商户编号
	 * 目前是QA商户号，2017年09月22日15:20:26
	 */
//	public static final String MERCHANT_NUMBER = "10040040565";

	/**
     * 白板在易宝的商户编号
     * 目前是生产商户号
     */
    public static final String MERCHANT_NUMBER = "10014929805";

    /**
     * 订单处理器前缀
	 */
	public static final String OPR_KEY = "OPR";
	/**
	 *订单前缀
	 */
	public static final String WB_TRX = "wbtrx";
	/**
	 *payment 前缀
	 */
	public static final String WB_PAY = "wbpay";

	public static final String DEFAULT_ENCODE = "utf-8";
	/**
	 * 短链地址
	 */
//	public static final String SHORTLINK_HOST = "http://10.151.31.89:8090/whitebroad-front/link/pay?trxid=";
    /**
	 * 二维码地址
	 */
//	public static final String QRURL_HOST = "http://10.151.31.89:8090/whitebroad-front/qr/toPay?qr_id=";
    /**
	 * 回调地址url
	 */
//	public static final String OPR_CALLBACK_URL="http://10.151.31.89:8090/whitebroad-front/opr/";

    /**
     * 短链地址
     */
    public static final String SHORTLINK_HOST = "https://wb.kqzmy.com/whitebroad-front/link/pay?trxid=";
    /**
     * 二维码地址
     */
    public static final String QRURL_HOST = "https://wb.kqzmy.com/whitebroad-front/qr/toPay?qr_id=";
    /**
     * 回调地址url
     */
    public static final String OPR_CALLBACK_URL = "https://wb.kqzmy.com/whitebroad-front/opr/";

	public static final String OPR_PAY_CALLBACK="pay";
	public static final String OPR_CS_CALLBACK="cs";

}
