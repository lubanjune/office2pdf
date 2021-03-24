package com.luban;

import com.luban.service.OnlyOfficeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Office2pdfApplicationTests {

    @Autowired
    private OnlyOfficeService onlyOfficeService;

    @Test
    void contextLoads() {
        String pdf = onlyOfficeService.convert("http://XXXXXXX.docx", "pdf");
        System.out.println("onlyoffice返回pdf路径：" + pdf);
    }

}
