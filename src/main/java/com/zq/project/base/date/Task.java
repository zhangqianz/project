package com.zq.project.base.date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangqian
 * @date 2023/6/25 16:52
 * @description:
 */
@Slf4j
@Component
public class Task {

    private static ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(20);

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    @PostConstruct
    public static void init(){
        long nextMillisEndWithMinute0or5 = getNextMillisEndWithMinute0or5(new Date());
        long deploy = nextMillisEndWithMinute0or5 - System.currentTimeMillis();
        log.info(String.valueOf(deploy));
        scheduledExecutorService.scheduleAtFixedRate(() ->{
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(localDateTime);
            Instant instant = localDateTime.toInstant(ZoneOffset.of("+8"));
            Date date = Date.from(instant);
            System.out.println(date);
            System.out.println("format.format(LocalDateTime.now())");
            System.out.println(format.format(new Date()));
        },0,10000,TimeUnit.MILLISECONDS);
    }

    public static long getNextMillisEndWithMinute0or5(Date baseTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseTime);
        int minute = calendar.get(Calendar.MINUTE);
        if (minute < 55) {
            int add = minute%10 < 5? 5 - minute%10 : 10 - minute%10;
            calendar.add(Calendar.MINUTE,add);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime().getTime();
        }
// 当前时间+1小时
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.HOUR_OF_DAY,1);
//        Date endTime = DateUtils.addHours(calendar.getTime(), 1);
        return calendar.getTime().getTime();
    }


    public static void ceshi(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setThreadNamePrefix("定时任务-");
        threadPoolTaskScheduler.initialize();

        // 定义一个 Runnable 任务
        Runnable task = () -> System.out.println("定时任务执行：" + new Date());
        // 使用 TaskScheduler 执行定时任务
        threadPoolTaskScheduler.schedule(task, new CronTrigger("0 0/5 * * * ?")); // 设置任务触发规则
    }

    static {
        ceshi();
    }

        public static void main(String[] args) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime roundedTime = now.truncatedTo(ChronoUnit.HOURS)
                    .plusMinutes((now.getMinute() / 5) * 5);
            LocalDateTime startOfInterval = roundedTime;
            LocalDateTime endOfInterval = roundedTime.plusMinutes(5);
            System.out.println("Start of interval: " + startOfInterval);
            System.out.println("End of interval: " + endOfInterval);
    }

}
