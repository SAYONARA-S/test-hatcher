package com.hatcher.app.service.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeItemInfoBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String header;

    private String name;

    private String msg;

    private int likenum;

    private int sharenum;

    private int commentnum;

    private ArrayList<String> imageURLlist = new ArrayList<String>();

    private ArrayList<FriendItemInfoBean> itemInfoBeanList = new ArrayList<FriendItemInfoBean>();



    public HomeItemInfoBean(JSONObject jsonObject) {
        try {
            this.setHeader(jsonObject.getString("header"));
        } catch (JSONException e) {
            e.printStackTrace();
            this.setHeader("");
        }
        try {
            this.setName(jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
            this.setName("");
        }
        try {
            this.setMsg(jsonObject.getString("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
            this.setMsg("");
        }


        try {
            this.setCommentnum(jsonObject.getInt("commentnum"));
        } catch (JSONException e) {
            e.printStackTrace();
            this.setCommentnum(0);
        }

        try {
            this.setLikenum(jsonObject.getInt("likenum"));
        } catch (JSONException e) {
            e.printStackTrace();
            this.setLikenum(0);
        }
        try {
            this.setSharenum(jsonObject.getInt("sharenum"));
        } catch (JSONException e) {
            e.printStackTrace();
            this.setSharenum(0);
        }

        try {
            JSONArray imagelist = jsonObject.getJSONArray("imagelist");
            for (int j = 0; j < imagelist.length(); j++) {
                imageURLlist.add(imagelist.get(j).toString());
            }
            this.setImageURLlist(imageURLlist);
        } catch (JSONException e) {
            e.printStackTrace();
            this.setImageURLlist(null);
        }

        try {
            JSONArray itemList = jsonObject.getJSONArray("comentList");
            for (int j = 0; j < itemList.length(); j++) {
                itemInfoBeanList.add(new FriendItemInfoBean((JSONObject) itemList.get(j)));
            }
            this.setItemInfoBeanList(itemInfoBeanList);
        } catch (JSONException e) {
            e.printStackTrace();
            this.setImageURLlist(null);
        }
    }

    public HomeItemInfoBean(int num)
    {
        setMsg("asdfasdfasdf" + num);
        setName("正大光明" + num);
        setHeader("http://avatar.csdn.net/9/7/0/1_mosibi.jpg");
        setLikenum(0 + num);
        setSharenum(1234 + num);
        setCommentnum(12344 + num);

        for (int j = 0; j < 4; j++) {
            imageURLlist.add("http://avatar.csdn.net/9/7/0/1_mosibi.jpg");
        }

        for (int j = 0; j < 4; j++) {
            itemInfoBeanList.add(new FriendItemInfoBean(j));
        }
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

    public int getLikenum() {
        return likenum;
    }

    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }

    public int getSharenum() {
        return sharenum;
    }

    public void setSharenum(int sharenum) {
        this.sharenum = sharenum;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }

    public ArrayList<String> getImageURLlist() {
        return imageURLlist;
    }

    public void setImageURLlist(ArrayList<String> imageURLlist) {
        this.imageURLlist = imageURLlist;
    }

    public ArrayList<FriendItemInfoBean> getItemInfoBeanList() {
        return itemInfoBeanList;
    }

    public void setItemInfoBeanList(ArrayList<FriendItemInfoBean> itemInfoBeanList) {
        this.itemInfoBeanList = itemInfoBeanList;
    }
}
