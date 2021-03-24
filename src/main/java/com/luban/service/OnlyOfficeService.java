package com.luban.service;

import com.luban.clients.OnlyOfficeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class OnlyOfficeService {

    @Value("${onlyoffice.url}")
    private String ONLY_URL;

    private OnlyOfficeClient onlyOfficeClient;
    private static final Logger log = LoggerFactory.getLogger(OnlyOfficeService.class);

    @PostConstruct
    public void init() {
        onlyOfficeClient = new OnlyOfficeClient(ONLY_URL);
    }

    public String convert(String fileUrl, String targetSuffix) {
        return onlyOfficeClient.sendPOST(fileUrl, targetSuffix);
    }

}
