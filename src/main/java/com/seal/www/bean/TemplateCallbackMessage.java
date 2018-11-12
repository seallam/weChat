package com.seal.www.bean;

/**
 * @author: seal
 * @Description: 微信模板消息回调msg实体
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-09-14 17:53
 */
public class TemplateCallbackMessage extends BaseMessage {
    private static final long serialVersionUID = -9183462018612602613L;

    private String MsgID;

    private String Status;

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        MsgID = msgID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
