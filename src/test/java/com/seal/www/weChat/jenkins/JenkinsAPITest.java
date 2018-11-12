package com.seal.www.weChat.jenkins;

import com.alibaba.fastjson.JSONObject;
import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import com.seal.www.weChat.WeChatApplicationTests;
import org.junit.Test;

/**
 * @author: seal
 * @Description: Jenkins测试
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-10-26 17:46
 */
public class JenkinsAPITest extends WeChatApplicationTests {



    @Test
    public void basicTest() {
        JenkinsClient client = JenkinsClient.builder()
                .endPoint("http://127.0.0.1:8090") // Optional. Defaults to http://127.0.0.1:8080
                .credentials("linxiao:123456") // Optional.
                .build();

        SystemInfo systemInfo = client.api().systemApi().systemInfo();
        System.out.println(JSONObject.toJSONString(systemInfo));


    }
}
