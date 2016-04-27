package atguigu.com.lingshixiaomiao.pager.scale.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/11 0011.
 */
public class TimeUtil {
    /**
     * 格式化时间戳为日期
     *
     * @param timeStr
     * @return
     */
    public static String getFormatData(String timeStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm 准时开始");

        String sd = sdf.format(new Date(Long.parseLong(timeStr)));

        return sd;
    }

    /**
     * 格式化毫秒单位时间为XX日XX小时XX秒X毫秒格式
     *
     * @param milliSecondTime
     * @return
     */
    public static String getFormatTime(long milliSecondTime) {

        long day = milliSecondTime / (24 * 60 * 60 * 1000);

        long hour = (milliSecondTime - day * 24 * 60 * 60 * 1000) / (60 * 60 * 1000);
        long minute = (milliSecondTime - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000) / (60 * 1000);
        long seconds = (milliSecondTime - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
        long millisecond = milliSecondTime - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - minute * 60 * 1000 - seconds * 1000;

        if (millisecond >= 1000) {
            millisecond = millisecond % 1000;
            seconds += millisecond / 1000;
        }

        if (seconds >= 60) {
            seconds = seconds % 60;
            minute += seconds / 60;
        }
        if (minute >= 60) {
            minute = minute % 60;
            hour += minute / 60;
        }
        String sd = "";
        String sh = "";
        String sm = "";
        String ss = "";
        String sms = "";

        if (day < 10) {
            sd = "0" + String.valueOf(day);
        } else {
            sd = String.valueOf(day);
        }

        if (hour < 10) {
            sh = "0" + String.valueOf(hour);
        } else {
            sh = String.valueOf(hour);
        }

        if (minute < 10) {
            sm = "0" + String.valueOf(minute);
        } else {
            sm = String.valueOf(minute);
        }

        if (seconds < 10) {
            ss = "0" + String.valueOf(seconds);
        } else {
            ss = String.valueOf(seconds);
        }

        if (millisecond < 100) {
            sms = "0";
        } else {
            sms = String.valueOf(millisecond).substring(0, 1);
        }

        return "仅剩：" + sd + "天" + sh + "小时" + sm + "分钟" + ss + "秒" + sms;
    }
}
