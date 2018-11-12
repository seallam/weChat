package com.seal.www.bean;

import com.google.common.collect.Maps;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: seal
 * @Description:
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-09-01 15:49
 */
@Component
@ConfigurationProperties(prefix = "props")
public class MessageConfigBean {

    private Map<String, String> weChatProps = Maps.newHashMap();

    @Bean
    public Map<String, String> getWeChatProps() {
        return weChatProps;
    }

    public void setWeChatProps(Map<String, String> weChatProps) {
        this.weChatProps = weChatProps;
    }
}
