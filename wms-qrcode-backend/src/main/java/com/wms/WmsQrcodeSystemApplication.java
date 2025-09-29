package com.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wms.mapper")
public class WmsQrcodeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsQrcodeSystemApplication.class, args);
    }
}
