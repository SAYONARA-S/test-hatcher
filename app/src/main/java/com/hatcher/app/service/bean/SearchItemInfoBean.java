package com.hatcher.app.service.bean;


import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class SearchItemInfoBean implements Serializable {

    public String header;
    public String name;
    public String msg;
    public int type;


    public SearchItemInfoBean(JSONObject jsonObject) {
        try {
            setHeader(jsonObject.getString("header"));
        } catch (Exception e) {
            setHeader("");
        }
        try {
            setName(jsonObject.getString("name"));
        } catch (Exception e) {
            setName("");
        }
        try {
            setMsg(jsonObject.getString("msg"));
        } catch (Exception e) {
            setMsg("");
        }
        try {
            setType(jsonObject.getInt("type"));
        } catch (Exception e) {
            setType(0);
        }
    }

    public SearchItemInfoBean(int num)
    {
        setMsg("asdfasdfasdf" + num);
        setName("正大光明" + num);
        setHeader("http://avatar.csdn.net/9/7/0/1_mosibi.jpg");
        setType(num);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
