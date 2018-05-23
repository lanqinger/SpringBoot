package com.lhm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan(basePackages = {"com.lhm.dao"})
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }

}
