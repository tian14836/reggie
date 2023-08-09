package com.fzt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@EnableCaching
public class TakeOutApplication {
    public static void main(String[] args) {
        log.info("项目启动了");
        SpringApplication.run(TakeOutApplication.class,args);
    }
}
