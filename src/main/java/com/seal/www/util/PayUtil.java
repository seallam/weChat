package com.seal.www.util;

import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: seal
 * @Description: 微信支付工具类
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-10-10 11:07
 */
@Component
public class PayUtil {
//    private WxPayService wxPayService;
//
//    @PostConstruct
//    public void init() {
//        wxPayService = new WxPayServiceImpl();
//        WxPayConfig wxPayConfig = new WxPayConfig();
//        wxPayConfig.setMchId("1505175001");
//        wxPayConfig.setAppId("wxa9a0fee2e2b7cbc2");
//        wxPayService.setConfig(wxPayConfig);
//    }
//
//    public void sendRedPack() throws WxPayException {
//        WxPaySendRedpackRequest wxPaySendRedpackRequest = new WxPaySendRedpackRequest();
//        wxPaySendRedpackRequest.setClientIp("127.0.0.1");
//        wxPayService.sendRedpack(wxPaySendRedpackRequest);
//    }
}
