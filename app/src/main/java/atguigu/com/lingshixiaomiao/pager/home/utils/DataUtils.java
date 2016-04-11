package atguigu.com.lingshixiaomiao.pager.home.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Liu_haiwei on 2016/4/11.
 * 日期时间的工具类
 */
public class DataUtils {

    /**
     * 指定日期格式 yyyyMMddHHmmss
     */
    public static final String DATE_FORMAT_1 = "yyyyMMddHHmmss";

    /**
     * 指定日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 指定日期格式 yyyy-MM-dd'T'HH:mm:ssZ
     */
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * 指定日期格式 yyyy-MM-dd
     */
    public static final String DATE_FORMAT_4 = "yyyy-MM-dd";

    /**
     * 指定日期格式 yyyy.M.d
     */
    public static final String DATE_FORMAT_5 = "yyyy.M.d";

    /**
     * 指定日期格式yyyy-MM-dd HH:mm
     */
    public static final String DATE_FORMAT_6 = "yyyy-MM-dd HH:mm";

    /**
     * 指定日期格式HH:mm
     */
    public static final String DATE_FORMAT_7 = "HH:mm";

    /**
     * 指定日期格式MM-dd HH:mm
     */
    public static final String DATE_FORMAT_8 = "MM-dd HH:mm";

    /**
     * 指定日期格式HH:MM:SS
     */
    public static final String DATE_FORMAT_9 = "HH:MM:SS";

    /**
     * 指定日期格式HH:mm
     */
    public static final String DATE_FORMAT_10 = "MM-dd";

    /**
     * 指定日期格式yy-MM-dd HH:mm
     */
    public static final String DATE_FORMAT_11 = "yy-MM-dd HH:mm";

    /**
     * 日期排序类型-升序
     */
    public final static int DATE_ORDER_ASC = 0;

    /**
     * 日期排序类型-降序
     */
    public final static int DATE_ORDER_DESC = 1;

    /**
     * 将毫秒转化为dd-HH-mm-ss
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        long second = time % 60;
        int day = (int) (time / (60 * 60 * 24));
        int hours = (int) ((time - day * 24 * 60 * 60) / (60 * 60));
        int minute = (int) ((time - day * 24 * 60 * 60 - hours * 60 * 60) / 60);
        if (day > 0) {
            return day + "天" + hours + ":" + minute + ":" + second;
        } else if (hours > 0) {
            return hours + ":" + minute + ":" + second;
        } else if (minute > 0) {
            return minute + ":" + second;
        } else if (second > 0) {
            return second + "";
        }

        return "";
    }

    /**
     * 根据指定格式，获取现在时间
     */
    public static String getNowDateFormat(String format) {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(currentTime);
    }

    /**
     * 把String日期转成制定格式
     */
    public static String getDateFormat(String getDateString, String format) {
        if (!TextUtils.isEmpty(getDateString)) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

            Date getDate = null;
            try {
                getDate = getFormat(DATE_FORMAT_6).parse(getDateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return getDateString;
            }

            return simpleDateFormat.format(getDate);
        }
        return getDateString;
    }


    public static SimpleDateFormat getFormat(String partten) {
        return new SimpleDateFormat(partten);
    }

    /**
     * 根据时间戳转成指定的format格式
     *
     * @param timeMillis
     * @param format
     * @return
     */
    public static String formatDate(String timeMillis, String format) {
        Date date = null;
        if (!TextUtils.isEmpty(timeMillis)) {
            try {
                date = new Date(Long.parseLong(timeMillis));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                date = new Date();
            }
        } else {
            date = new Date();
        }

        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
