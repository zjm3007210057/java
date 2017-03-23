package com.design.factory;

/**
 * Created by zjm on 2017/3/22.
 */
public class IdCard extends Product {

    private String ower;

    private String idNum;

    public IdCard(String ower, String idNum) {
        this.ower = ower;
        this.idNum = idNum;
    }

    public void use() {
        System.out.println(ower + "使用号码为" + idNum + "的ID卡");
    }

    public String getOwer() {
        return ower;
    }

    public String getIdNum() {
        return idNum;
    }
}
