package com.seal.www.util;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: seal
 * @Description:
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-10-08 16:07
 */
@Component
public class WeChatUtil {
    private Logger logger = LoggerFactory.getLogger(WeChatUtil.class);
    private WxMpService wxMpService;

    @PostConstruct
    public void init() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId("wxe83b0d4494c2fdb7");
        wxMpConfigStorage.setSecret("7921297423c2693c8f54e80bf153c7c0");
        wxMpConfigStorage.setToken("ered2w352d");
        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
    }

    public WxMpService getWxMpService() {
        return wxMpService;
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public Map<String, String> parseXml(InputStream inputStream) throws Exception {
        try {
            if (inputStream == null) {
                return null;
            }
            Map<String, String> map = new HashMap<>();// 将解析结果存储在HashMap中
            SAXReader reader = new SAXReader();// 读取输入流
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();// 得到xml根元素
            List<Element> elementList = root.elements();// 得到根元素的所有子节点
            for (Element e : elementList) {        // 遍历所有子节点
                map.put(e.getName(), e.getText());
            }
            return map;
        } catch (DocumentException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new Exception(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();        // 释放资源
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }
}
