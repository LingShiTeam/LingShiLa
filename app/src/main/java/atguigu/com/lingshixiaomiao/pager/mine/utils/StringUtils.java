package atguigu.com.lingshixiaomiao.pager.mine.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lanmang on 2016/4/16.
 */
public class StringUtils {

    /**
     * 去除换行符
     * 注：\n 回车(\u000a)
     \t 水平制表符(\u0009)
     \s 空格(\u0008)
     \r 换行(\u000d)
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
