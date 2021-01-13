package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZhuanbaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhuanbaApplication.class, args);
    }
}
