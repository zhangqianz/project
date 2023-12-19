package com.zq.project.base.date;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangqian
 * @date 2023/6/27 13:46
 * @description:
 */
public class Ceshi {
    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        System.out.println(LocalDate.now().getDayOfWeek().getValue());
        System.out.println(LocalDateTime.now().getHour());
        LocalDateTime localDateTime = LocalDate.now().atTime(LocalDateTime.now().getHour(), 0, 0);
        System.out.println(localDateTime);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
//        Thread.sleep(3000);

        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
        System.out.println(Duration.between(now1, now).toMillis());
        System.out.println(Duration.between(now, now1).toMillis());


        int dayOfMonth = now.getDayOfMonth();
        System.out.println(dayOfMonth);

        LocalDateTime localDateTime1 = now.minusDays(dayOfMonth - 1);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = now.minusWeeks(1);

        System.out.println(localDateTime2.isAfter(localDateTime1));

        System.out.println(System.currentTimeMillis());
        System.out.println(URLEncoder.encode("209037hnwasu_hwc6b368f1f10b14fea87b4e4dd4fbdf17messageContent=停车场-厂区:厂区已有空余停车场,请前往测试3公司templateId=2031012220113userNumber=15926114162"+System.currentTimeMillis()+"123456", StandardCharsets.UTF_8.name()));
    }
}
