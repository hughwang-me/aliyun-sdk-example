package com.uwjx.aliyunsdk.oss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
@Slf4j
public class OssService {

    @Resource
    PublicACLExample publicACLExample;

    @PostConstruct
    public void run(){
        publicACLExample.getUrl();
    }
}
