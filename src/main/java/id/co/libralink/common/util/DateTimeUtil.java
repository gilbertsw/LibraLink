package id.co.libralink.common.util;

import org.apache.commons.lang3.time.DateUtils;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateTimeUtil extends DateUtils {

    public static final String PATTERN_ISO_DATE = "yyyy-MM-dd";
    public static final String PATTERN_ISO_DATE_REVERSE = "dd-MM-yyyy";
    public static final String PATTERN_ISO_TIME = "HH:mm:ss";
    public static final String PATTERN_ISO_DATE_TIME = PATTERN_ISO_DATE + "'T'" + PATTERN_ISO_TIME + ".SSSZ";
    public static final String PATTERN_ISO_DATE_TIME_ALTERNATE = PATTERN_ISO_DATE + "'T'" + PATTERN_ISO_TIME + ".SSS'Z'";

    public static final DateTimeFormatter FORMATTER_LOCAL_DATE_ISO = DateTimeFormatter.ofPattern(PATTERN_ISO_DATE);
    public static final DateTimeFormatter FORMATTER_DATE_TIME_ISO_ALT = DateTimeFormatter.ofPattern(PATTERN_ISO_DATE_TIME_ALTERNATE);

    private DateTimeUtil() {
        // can't instantiate
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String format(Instant time, String pattern) {
        return DateTimeFormatter
                .ofPattern(pattern)
                .withZone(ZoneId.systemDefault())
                .format(time);
    }

    public static LocalDateTime instanceDateTime(int year, int month, int day, LocalTime localTime) {
        return LocalDateTime.of(LocalDate.of(year, month, day),localTime);
    }

    public static LocalDateTime atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.with(LocalTime.MIN);
    }

    public static LocalDateTime atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.with(LocalTime.MAX);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
