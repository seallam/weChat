package com.seal.www.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

/**
 * @author: seal
 * @Description: 测试
 * @company: xingfeiinc
 * @e-mail: linxiao@xingfeiinc.com
 * @date: 2018-10-19 15:39
 */
@Controller
@RequestMapping("test")
public class TestController {

    @PostMapping("post")
    public @ResponseBody
    JSONObject test(@RequestParam("sign") String sign, @RequestParam("c") int c, @RequestBody String data) {
        JSONObject result = new JSONObject();
        System.out.println("sign:" + sign);
        System.out.println("c:" + c);
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decode = decoder.decode(data);
            System.out.println(new String(decode));
//            System.out.println(data);
        } catch (Exception e) {
            result.put("result", "failed");
        }
        return result;
    }
}
