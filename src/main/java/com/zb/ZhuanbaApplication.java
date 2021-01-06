package com.zb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zb.mapper")
public class ZhuanbaApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZhuanbaApplication.class, args);
  }
}
