package com.bwie.tianmengjie20171106.evenbus;

/**
 * Created by T_baby on 17/11/07.
 */
//发送信息的实体类
public class FirstEvent {
    private String mMsg;
    public FirstEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
