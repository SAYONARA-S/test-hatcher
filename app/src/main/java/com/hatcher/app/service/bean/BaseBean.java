package com.hatcher.app.service.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-5-5.
 */
public class BaseBean implements Serializable {



    private String code;
    private String rtnStr;
    private String undoCnt;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRtnStr() {
        return rtnStr;
    }

    public void setRtnStr(String rtnStr) {
        this.rtnStr = rtnStr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUndoCnt() {
        return undoCnt;
    }

    public void setUndoCnt(String undoCnt) {
        this.undoCnt = undoCnt;
    }
}
