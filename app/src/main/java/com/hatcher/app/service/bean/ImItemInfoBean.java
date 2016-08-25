package com.hatcher.app.service.bean;


import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class ImItemInfoBean implements Serializable {

    public String header;
    public String name;
    public String msg;
    public long time;


    public ImItemInfoBean(JSONObject jsonObject) {
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
            setTime(jsonObject.getLong("time"));
        } catch (Exception e) {
            setTime(0);
        }
    }

    public ImItemInfoBean(int num)
    {
        setMsg("asdfasdfasdf" + num);
        setName("正大光明" + num);
        setHeader("http://avatar.csdn.net/9/7/0/1_mosibi.jpg");
        setTime(new Date().getTime());
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
