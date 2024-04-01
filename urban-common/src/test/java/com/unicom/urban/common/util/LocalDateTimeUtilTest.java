package com.unicom.urban.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LocalDateTimeUtilTest {


    @Test
    public void thisQuarterStartOfDayTest() {
        LocalDate today = LocalDate.now();
        int dayOfMonth = 1;
        int hour = 0;
        int minute = 0;
        int second = 0;
        LocalDateTime localDateTime = LocalDateTimeUtil.thisQuarterStartOfDay();
        int monthValue = today.getMonthValue();
        if (monthValue <= 3) {
            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 1, dayOfMonth, hour, minute, second));
        } else if (monthValue <= 6) {
            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 4, dayOfMonth, hour, minute, second));
        } else if (monthValue <= 9) {
            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 7, dayOfMonth, hour, minute, second));
        } else {
            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 10, dayOfMonth, hour, minute, second));
        }
    }

//    @Test
//    public void thisQuarterEndOfDayTest() {
//        LocalDate today = LocalDate.now();
//        int dayOfMonth = 31;
//        int hour = 23;
//        int minute = 59;
//        int second = 59;
//        LocalDateTime localDateTime = LocalDateTimeUtil.thisQuarterEndOfDay();
//        int monthValue = today.getMonthValue();
//        if (monthValue <= 3) {
//            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 3, dayOfMonth, hour, minute, second));
//        } else if (monthValue <= 6) {
//            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 6, dayOfMonth, hour, minute, second));
//        } else if (monthValue <= 9) {
//            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 9, dayOfMonth, hour, minute, second));
//        } else {
//            Assert.assertEquals(localDateTime, LocalDateTime.of(today.getYear(), 12, dayOfMonth, hour, minute, second));
//        }
//    }

    @Test
    public void betweenTest() {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 20, 10, 31, 10);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 1, 26, 23, 59, 59);
        List<LocalDate> betweenDate = LocalDateTimeUtil.between(startDateTime, endDateTime);
        Assert.assertEquals(7, betweenDate.size());
    }

}
