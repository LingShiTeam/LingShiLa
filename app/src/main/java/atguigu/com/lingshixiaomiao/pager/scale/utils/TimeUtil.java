package atguigu.com.lingshixiaomiao.pager.scale.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/11 0011.
 */
public class TimeUtil {
    /**
     * 格式化时间戳为日期格式
     *
     * @param time 时间戳
     * @return
     */
    public static String getLeftDate(long time) {

        SimpleDateFormat sdf = new SimpleDateFormat("仅剩：dd天HH小时mm分ss秒S");

        String sd = sdf.format(new Date(time));

        return sd;
    }

    public static String getFormatData(String timeStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm 准时开始");

        String sd = sdf.format(new Date(Long.parseLong(timeStr)));

        return sd;
    }
}
