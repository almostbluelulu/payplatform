package com.shentu.g3.app.pay.route;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.UUID;

/**
 * title: api变更事件<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author wenkang.zhang
 * @version 1.0.0
 * @since 16-5-12 下午4:13
 */
public class ApiReloadPacket implements Serializable {

    private static final long serialVersionUID = 6965500862544021213L;

    private String packetId = UUID.randomUUID().toString();

    private Long id;

    private String uri;

    //when backendApp is not blank,not only reload the api(s) belong to this backendApp,but also reCreate the BackendAppClassLoader before reload the api(s).
    private String backendApp;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ApiReloadPacket() {

    }

    public ApiReloadPacket(Long id, String uri, String backendApp) {
        this.id = id;
        this.uri = uri;
        this.backendApp = backendApp;
    }

    public String getBackendApp() {
        return backendApp;
    }

    public void setBackendApp(String backendApp) {
        this.backendApp = backendApp;
    }
}
