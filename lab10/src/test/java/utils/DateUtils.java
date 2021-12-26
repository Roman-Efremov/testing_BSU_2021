package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.chop;


public class DateUtils {

    public static String formatDateToRussian(Date date, String format) {
        return new SimpleDateFormat(format, new Locale("ru")).format(date);
    }

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getNextDate(Date date, int numberOfDays) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date d = getCurrentDate();
        System.out.println(chop(formatDateToRussian(d, "dd MMM")));
    }
}
