package com.uwjx.aliyunsdk.oss;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Calendar;

@Service
@Slf4j
public class PublicACLExample {

    @Value("${aliyun-oss.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun-oss.accessKeySecret}")
    String accessKeySecret;
    @Value("${aliyun-oss.endpoint}")
    String endpoint;
    @Value("${aliyun-oss.bucketName}")
    String bucketName;

    public void upload(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传Byte数组。
        byte[] content = "Hello OSS".getBytes();
        ossClient.putObject(bucketName, "hello", new ByteArrayInputStream(content));
        ossClient.shutdown();
    }

    public void getUrl(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND , 60);
        URL url = ossClient.generatePresignedUrl(bucketName , "1.png" , calendar.getTime());
        log.warn("url -> {}" , JSON.toJSONString(url));
        ossClient.shutdown();
    }
}
