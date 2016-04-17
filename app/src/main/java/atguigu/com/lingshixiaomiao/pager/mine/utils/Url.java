package atguigu.com.lingshixiaomiao.pager.mine.utils;

/**
 * Created by lanmang on 2016/4/8.
 */
public class Url {
    /**
     * 首页头部链接
     */
    public static final String HOME_TOP_URL = "http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=" +
            "20160406223600&sig=5ad479a0b6bcaff9&wssig=a62fcd9fb5171bca&os_type=3&version=18&channel_name=feibo&srv=2201";

    /**
     * 更新,旧版本的请求
     */
    public static final String MINE_UPDATE_OLD = "http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=" +
            "20160411154845&sig=7c08c2b1069f3f43&wssig=21bdcd495557a7fc&os_type=3&version=18&channel_name=feibo&srv=1001";

    /**
     * 更新,新版本的请求
     */
    public static final String MINE_UPDATE_NEW = "http://api.ds.lingshi.cccwei.com/?cid=759713&uid=181772&tms=" +
            "20160411155013&sig=33b8feee2b028997&wssig=7cc0d4f326673cf5&os_type=3&version=22&channel_name=feibo&srv=1001";

    /**
     * 登录请求地址
     */
    public static final String[] LOGIN_URLS = {"http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=20160411213335&" +
            "sig=4802a06ac4102cd5&wssig=b6bbe12f8bf0bc6e&os_type=3&version=22&channel_name" +
            "=feibo&srv=2102&mobi_num=", "&pwd=", "&opt=0"};

    /**
     * 注册地址
     */
    public static final String REGISTER_URL = "http://testapi.ds.lingshi.cccwei.com/api/Tpl/default/Login/register.html";

    /**
     * 忘记密码地址
     */
    public static final String FORGET_PASSWORD_URL = "http://testapi.ds.lingshi.cccwei.com/api/Tpl/default/Login/forgetPwd.html";

    /**
     * 修改昵称
     */
    public static final String[] CHANGE_NICKNAME_URL = {"http://api.ds.lingshi.cccwei.com/?cid=760294&uid=",
            "&tms=20160412133931&sig=6ada4fd86a05c0d6&wssig=31307b0da4fe39b7&os_type=" +
                    "3&version=22&channel_name=feibo&srv=2710&nickname="};

    /**
     * 获取地址
     */
    public static final String[] MANAGE_ADDRESS_URL = {"http://api.ds.lingshi.cccwei.com/?cid=760294&uid=",
            "&tms=20160413222000&sig=076d43ff864375cc&wssig=0d607100d2dd02b3&os_type=3&version=22&channel_name=feibo&srv=2801"};

    /**
     * 修改默认地址
     */
    public static final String[] CHANGE_DEFAULT_ADDRESS_URL = {"http://api.ds.lingshi.cccwei.com/?cid=760294&uid=",
            "&tms=20160414174558&sig=7080fe536a2e4402&wssig=d7a53e96c68da90e&os_type=3&version=22&channel_name=" +
                    "feibo&srv=2803&opt=1&add_id="};

    /**
     * 删除地址
     */

    public static final String[] DELETE_ADDRESS_URL = {"http://api.ds.lingshi.cccwei.com/?cid=760294&uid=",
            "&tms=20160414175538&sig=3a59927614358bb8&wssig=921babdf3bc2b1f2&os_type=3&version=22&channel_name=feibo&srv=2803&opt=",
            "&add_id="};

    /**
     * 编辑或新增地址 区别是新增地址add_id = 0 编辑为内部存的值  uid add_id name phone province city proper full_add type
     */
    public static final String[] EDIT_ADDRESS_URL = {"http://api.ds.lingshi.cccwei.com/?cid=760294&uid=",
            "&tms=20160414211634&sig=92766e9d648e3842&wssig=e6ef18ce7211c61b&os_type=3" +
                    "&version=22&channel_name=feibo&srv=2802&add_id=",
            "&name=", "&phone=", "&province=", "&city=", "&proper=", "&full_add=", "&type="};
}
