package com.lhm.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //定时任务，每隔60秒执行一次
    @Scheduled(fixedRate = 60*1000)
    public void getCurrentTime() {
        System.out.println("当前时间：" + dateTimeFormatter.format(LocalDateTime.now()));
    }



}
