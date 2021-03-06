package com.lhm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"com.lhm.dao"})
@ComponentScan(basePackages = {"com.lhm"})
//@EnableScheduling //定时任务配置
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }

}
