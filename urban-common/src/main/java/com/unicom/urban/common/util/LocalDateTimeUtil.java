package com.unicom.urban.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liukai
 */
public abstract class LocalDateTimeUtil {

    /**
     * 判断是否为工作日（周一到周五）
     */
    public static boolean isWorkday(LocalDate localDate) {
        return !isWeekend(localDate);
    }

    /**
     * 是否为周末（周六周日）
     */
    public static boolean isWeekend(LocalDate localDate) {
        return DayOfWeek.SATURDAY.equals(localDate.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(localDate.getDayOfWeek());
    }

    /**
     * 获取本周第一天，也就是星期一
     */
    public static LocalDateTime thisWeekStartOfDay() {
        return LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
    }

    /**
     * 获取本月第一天
     */
    public static LocalDateTime thisMonthStartOfDay() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
    }

    /**
     * 获取今年第一天
     */
    public static LocalDateTime thisYearStartOfDay() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).atStartOfDay();
    }

    /**
     * 获取当前日期所在季度的开始日期
     */
    public static LocalDateTime thisQuarterStartOfDay() {
        LocalDate now = LocalDate.now();
        Month firstMonthOfQuarter = now.getMonth().firstMonthOfQuarter();
        return LocalDateTime.of(now.getYear(), firstMonthOfQuarter, 1, 0, 0, 0);
    }


    /**
     * 获取当前日期所在季度的结束日期
     */
    public static LocalDateTime thisQuarterEndOfDay() {
        LocalDate now = LocalDate.now();
        Month endMonthOfQuarter = Month.of(now.getMonth().firstMonthOfQuarter().getValue() + 2);
        return LocalDateTime.of(now.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(now.isLeapYear()), 23, 59, 59);
    }

    /**
     * 计算经过了哪些日期
     *
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     */
    public static List<LocalDate> between(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = endDateTime.toLocalDate();
        List<LocalDate> localDates = new ArrayList<>();
        // 如果为同一天
        if (startDate.equals(endDate)) {
            localDates.add(startDate);
            return localDates;
        }
        long day = startDate.until(endDate, ChronoUnit.DAYS);
        for (long i = 0; i <= day; i++) {
            localDates.add(startDate.plusDays(i));
        }
        return localDates;
    }

}
