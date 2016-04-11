package atguigu.com.lingshixiaomiao.pager.mine.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lanmang on 2016/4/11.
 * 手机号工具类
 */
public class PhoneUtils {

    /**
     * 判断是否是手机号
     *
     *  要更加准确的匹配手机号码只匹配11位数字是不够的，比如说就没有以144开始的号码段，

     　　故先要整清楚现在已经开放了多少个号码段，国家号码段分配如下：

     　　移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188

     　　联通：130、131、132、152、155、156、185、186

     　　电信：133、153、180、189、（1349卫通）
     */
    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 电话号码验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null,p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if(str.length() >9)
        {	m = p1.matcher(str);
            b = m.matches();
        }else{
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }
}
