package com.seal.www.weChat;

import com.google.common.collect.Lists;
import com.seal.www.util.WeChatUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatApplicationTests {

	@Resource
	private WeChatUtil weChatUtil;

	@Test
	public void contextLoads() {
	}

	@Test
	public void sendMsgTest() throws WxErrorException {
		WxMpService wxMpService = weChatUtil.getWxMpService();
		WxMpTemplateMsgService templateMsgService = wxMpService.getTemplateMsgService();
		WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
		wxMpTemplateMessage.setUrl("");
		wxMpTemplateMessage.setTemplateId("hCiakSN8pGEB7oaGXk3GSgdp5jk-zG2GdA2iz-q-1AE");
		List<WxMpTemplateData> templateDataList = Lists.newArrayList();
		WxMpTemplateData wxMpTemplateData1 = new WxMpTemplateData();
		wxMpTemplateData1.setName("first");
		wxMpTemplateData1.setValue("亲爱的tin");
		templateDataList.add(wxMpTemplateData1);
		WxMpTemplateData wxMpTemplateData2 = new WxMpTemplateData();
		wxMpTemplateData2.setName("token");
		wxMpTemplateData2.setValue("你是猪");
		templateDataList.add(wxMpTemplateData2);
		WxMpTemplateData wxMpTemplateData3 = new WxMpTemplateData();
		wxMpTemplateData3.setName("remark");
		wxMpTemplateData3.setValue("哈哈哈");
		templateDataList.add(wxMpTemplateData3);
		wxMpTemplateMessage.setData(templateDataList);
		wxMpTemplateMessage.setToUser("ogJcR1Q2pM3pO8DFCAdQKbTzNkeA");
		templateMsgService.sendTemplateMsg(wxMpTemplateMessage);
	}
}
