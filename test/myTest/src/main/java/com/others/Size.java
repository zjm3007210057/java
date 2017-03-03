package com.others;

/**
 * Created by zhjm on 2016/12/20.
 */
public enum Size {

    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String value;

    private Size(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
