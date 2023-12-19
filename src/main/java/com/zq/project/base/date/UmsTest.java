package com.zq.project.base.date;

import com.alibaba.fastjson2.JSONObject;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jcajce.provider.digest.SM3;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

/**
 * 发送短信
 */
public class UmsTest {

    public static void main(String[] args) throws Exception {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(factory);
        // 支持中文编码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName(StandardCharsets.UTF_8.toString())));
        long currentTimeMillis = System.currentTimeMillis();
        String requestId = currentTimeMillis+ randon6();
        String spCode = "209037";
        String appKey = "hnwasu_hw";
        String appSec = "c6b368f1f10b14fea87b4e4dd4fbdf17";
        String messageContent = "停车场-厂区: 厂区已有空余停车场, 请前往黄湾测试公司";
        String phone = "15926114162";
        String templateId = "2031012220113";

        JSONObject ums = new JSONObject();
        ums.put("templateId", "2031012220113");
        ums.put("messageContent", messageContent);
        ums.put("userNumber", "15926114162");
        String reqContent = "messageContent=" + messageContent +"&templateId=" + templateId + "&userNumber=" + phone;
        String content = URLEncoder.encode(reqContent);
        String singContent = spCode + appKey + appSec + content + requestId;
        SM3Digest sm3Digest=new SM3Digest();
        sm3Digest.update(singContent.getBytes(StandardCharsets.UTF_8),0,singContent.getBytes(StandardCharsets.UTF_8).length);
        byte[] hash = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(hash, 0);
        String sign= byteArrayToHexString(hash);
        System.out.println(sign);
        //远程调用获取数据
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("x-request-id",requestId);
        httpHeaders.add("x-sp-code",spCode);
        httpHeaders.add("x-app-key",appKey);
        httpHeaders.add("x-sign",sign);
        HttpEntity<Map<String, Object>> http = new HttpEntity<>(ums, httpHeaders);
        //post方式
        System.out.println(restTemplate.postForObject("https://api.ums86.com/api/sms/send", http, com.alibaba.fastjson.JSONObject.class));
    }

    public static String randon6(){
        String test = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = test.length();
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for(int i =0; i< 6; i ++){
            int index = random.nextInt(length);
            sb.append(test.charAt(index));
        }
        return sb.toString();
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}