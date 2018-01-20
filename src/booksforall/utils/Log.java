package booksforall.utils;

import java.util.Calendar;

public class Log {

    public static void e(String tag, String func, String message, Throwable e) {

        String logStr = String.format("|ERROR| %s %s.%s: ", getLogDate(), tag, func);
        String stack = "";
        if (e != null) {
            stack = getThrowableStack(logStr.length(), e);
        }
        logStr += message + stack;

        System.err.println(logStr);
    }

    public static void l(String tag, String func, String message) {
        String logStr = String.format("|INFO | %s %s.%s: %s", getLogDate(), tag, func, message);
        System.out.println(logStr);
    }

    private static String getLogDate() {
        Calendar calendar = Calendar.getInstance();
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        String year = calendar.get(Calendar.YEAR) + "";
        String hours = calendar.get(Calendar.HOUR_OF_DAY) + "";
        String minutes = calendar.get(Calendar.MINUTE) + "";
        String seconds = calendar.get(Calendar.SECOND) + "";
        String milliseconds = calendar.get(Calendar.MILLISECOND) + "";

        day = (day.length() == 1 ? "0" : "") + day;
        month = (month.length() == 1 ? "0" : "") + month;
        hours = (hours.length() == 1 ? "0" : "") + hours;
        minutes = (minutes.length() == 1 ? "0" : "") + minutes;

        if (milliseconds.length() == 1) {
            milliseconds = "00" + milliseconds;
        } else if (milliseconds.length() == 2) {
            milliseconds = "0" + milliseconds;
        } else {
            milliseconds = milliseconds.substring(0, 3);
        }
        return day + "-" + month + "-" + year + " " + hours + ":" + minutes + ":" + seconds + "." + milliseconds;
    }

    private static String getThrowableStack(int charsCountPrefix, Throwable e) {
        String prefixStr = "";
        for (int i = 0; i < charsCountPrefix; i++) {
            prefixStr += " ";
        }
        String error = "";
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement aTrace : trace) {
            error += prefixStr + aTrace.toString() + "\n";
        }
        error = (error.length() > 0 ? "\n" : "") + error;
        return error;
    }

    public static void lt(String tag, String func, String message) {
        Log.l(tag, func, "\n***********************************\n" + message + "\n***********************************");
    }
}
