package com.qgstudio.anywork.fgrade;

/**
 * Created by chenyi on 2017/7/11.
 */

public class GradeInfo {

    public static final int TYPE1 = 0;
    public static final int TYPE2 = 1;

    private int type;
    private String info;

    public GradeInfo(int aType, String aInfo) {
        super();
        type = aType;
        info = aInfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
