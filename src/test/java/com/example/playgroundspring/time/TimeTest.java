package com.example.playgroundspring.time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;

public class TimeTest {

    @Test
    void date_test() {
        Date date = new Date();
        System.out.println("date.getTime:" + date.getTime());
        System.out.println("date.toString: " + date.toString());
    }

    @Test
    void instant_test() {
        Instant now = Instant.now();
        System.out.println("now.getEpochSecond(): " + now.getEpochSecond());
        System.out.println("now.getNano(): " + now.getNano());
    }

    @Test
    void localDate_test() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
    }

    @Test
    void localTime_test() {
        LocalTime now = LocalTime.now();
        System.out.println(now);
    }

    @Test
    void localDateTime_test() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    @Test
    void zonedDateTime_test() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
