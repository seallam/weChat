package com.seal.www.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.seal.www.bean.BaseMessage;
import com.seal.www.bean.TemplateCallbackMessage;
import com.seal.www.bean.TextMessage;
import com.seal.www.util.MessageUtil;
import com.seal.www.util.WeChatUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpSubscribeMsgService;
import me.chanjar.weixin.mp.api.impl.WxMpSubscribeMsgServiceImpl;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

/**
 * @author: seal
 * @Description: wechat
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-09-11 15:23
 */
@Controller
@RequestMapping("weChat")
public class WeChatController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private WeChatUtil weChatUtil;

    @RequestMapping(value = "checkToken", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    String checkToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            WxMpService wxMpService = weChatUtil.getWxMpService();
            if (request.getMethod().equals(RequestMethod.GET.name())) {
                String signature = request.getParameter("signature");
                String timestamp = request.getParameter("timestamp");
                String nonce = request.getParameter("nonce");
                String echostr = request.getParameter("echostr");
                boolean flag = wxMpService.checkSignature(timestamp, nonce, signature);
                if (flag) {
                    return echostr;
                }
            } else {
                String dataStr, text = "";
                logger.info("微信网关接收到消息推送,即将处理消息...");
                Map<String, String> responseData = weChatUtil.parseXml(request.getInputStream());
                logger.info("微信网关接收到消息推送,处理消息成功,收到数据为:{}", responseData);
                if (MapUtils.isNotEmpty(responseData)) {
                    String event = MapUtils.getString(responseData, "Event");
                    String msgType = MapUtils.getString(responseData, "MsgType");
                    logger.info("微信网关接收到消息推送,处理消息成功,事件类型为:{},消息类型为:{}", event, msgType);
                    dataStr = JSONObject.toJSONString(responseData);
                    BaseMessage baseMessage;
                    String toUserName, fromUserName;
                    if (StringUtils.isNotBlank(event)) {
                        switch (event) {
                            case MessageUtil.EVENT_SUB:
                                baseMessage = JSONObject.parseObject(dataStr, new TypeReference<BaseMessage>() {
                                });
                                String menuText = MessageUtil.menuText();
                                toUserName = baseMessage.getToUserName();
                                fromUserName = baseMessage.getFromUserName();
                                text = MessageUtil.initText(toUserName, fromUserName, menuText);
                                logger.info("最终回复内容为:{}", text);
                                break;
                            case "TEMPLATESENDJOBFINISH":
                                baseMessage = JSONObject.parseObject(dataStr, new TypeReference<TemplateCallbackMessage>() {
                                });
                                break;
                            default:
                                break;
                        }
                    } else if (StringUtils.isNotBlank(msgType)) {
                        switch (msgType) {
                            case MessageUtil.MESSAGE_TEXT:
                                baseMessage = JSONObject.parseObject(dataStr, new TypeReference<TextMessage>() {});
                                String content = ((TextMessage) baseMessage).getContent();
                                toUserName = baseMessage.getToUserName();
                                fromUserName = baseMessage.getFromUserName();
                                if ("你好帅".equals(content)) {
                                    text = MessageUtil.initText(toUserName, fromUserName, "好啦知道啦");
                                } else {
                                    text = MessageUtil.initText(toUserName, fromUserName, "别瞎bb");
                                }
                                break;
                        }
                    }
                }
                return text;
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("qrCode")
    public String qrCode() {
        WxMpService wxMpService = weChatUtil.getWxMpService();
        String url = wxMpService.buildQrConnectUrl("http://111.231.252.157/getCode", "snsapi_base", "ok");
        return "redirect:" + url;
    }

    @GetMapping("getCode")
    public String getCode(String code, String state) {
        System.out.println(code);
        System.out.println(state);
        return "success";
    }
}
