package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories("com.zb.repository")
@EnableElasticsearchRepositories("com.zb.elasticsearch")
public class ZhuanbaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhuanbaApplication.class, args);
    }
}
