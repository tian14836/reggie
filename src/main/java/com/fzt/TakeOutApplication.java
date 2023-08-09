package com.fzt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@EnableCaching
@EnableSwagger2
public class TakeOutApplication {
    public static void main(String[] args) {
        log.info("项目启动了");
        SpringApplication.run(TakeOutApplication.class,args);
    }
}
