package com.seal.www.bean;

/**
 * @author: seal
 * @Description:
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-10-09 15:13
 */
public class TextMessage extends BaseMessage {
    private static final long serialVersionUID = -3215259262673633873L;

    private String Content;

    private String MsgID;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        this.MsgID = msgID;
    }
}
