package com.netty.chapter7;


import java.io.Serializable;

/**
 * Created by zhjm on 2016/10/26.
 */
public class SubscribeResp implements Serializable{

    private static final long serialVersionUID = 8524510037455094806L;

    private int subReqId;
    private int respCode;
    private String desc;

    public int getSubReqId() {
        return subReqId;
    }

    public void setSubReqId(int subReqId) {
        this.subReqId = subReqId;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subReqId=" + subReqId +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
