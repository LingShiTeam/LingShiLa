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
     * 注册地址 第一步 输入手机号
     */
    public static final String REGISTER_URL = "http://testapi.ds.lingshi.cccwei.com/api/Tpl/default/Login/register.html";
    /**
     * 注册地址 第二步 设置密码
     */
    public static final String REGISTER_URL2 = "http://testapi.ds.lingshi.cccwei.com/api/Tpl/default/Login/loginPwd.html";

    public static final String[] REGISTER_URL_CHECK = {"http://api.ds.lingshi.cccwei.com/?cid=759713&uid=0&tms=201604182" +
            "25946&sig=210068a5612119e9&wssig=5ed755c634dc49e0&os_type=3&version=22&channel_name=feibo&srv=2105&mobi_num=", "&opt=0"};
    public static final String[] REGISTER_URL_CHECK2 = {"http://api.ds.lingshi.cccwei.com/?cid=759713&uid=0&tms=201604190" +
            "85505&sig=bfe15db7ff82fb2d&wssig=8f94e72dfaaf9d08&os_type=3&version=22&channel_name=feibo&srv=2102" +
            "&mobi_num=", "&pwd=", "&msg_code=", "&opt=1"};

    public static final String[] CHECK_SMS = {"http://api.ds.lingshi.cccwei.com/?cid=759713&uid=0&tms=20160419152627&sig=899f21881ac" +
            "ece98&wssig=c9ebf5d73bd5f6d0&os_type=3&version=22&channel_name=feibo&srv=2103&mobi_num=", "&img_code=null"};

    public static final String USERAGREEMENT = "http://api.ds.lingshi.cccwei.com/?cid=759713&uid=0&tms=20160419092013&sig=2a1634e2380aee6d&wssig=7d7ad728981d6d81&os_type=3&version=22&channel_name=feibo&srv=2104";

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

    public static final String[] COLLECTION_GOODS_URL = {"http://api.ds.lingshi.cccwei.com/?cid=759713&" +
            "uid=", "&tms=20160419191051&sig=10bd8b3956ec4e5f&wssig=a0d6ab092720d3d2&os_type=3" +
            "&version=22&channel_name=feibo&srv=2804&pg_cur=", "&pg_size=", "&since_id=", "&type=0"};
    public static final String[] COLLECTION_SPECIAL_URL = {"http://api.ds.lingshi.cccwei.com/?cid=759713&" +
            "uid=", "&tms=20160420104346&sig=91de33e5e9cde2bc&wssig=ff30bb53d45ddeea&os_type=3" +
            "&version=22&channel_name=feibo&srv=2806&pg_cur=", "&pg_size=", "&type=1&since_id="};

    //http://api.ds.lingshi.cccwei.com/?cid=759713&uid=181772&tms=20160419210128&sig=e276acb544f6e0f1&wssig
    // =36816c644cb06345&os_type=3&version=22&channel_name=feibo&srv=2804&pg_cur=2&pg_size=20&since_id=6856&type=0

    public static final String[] REMOVE_COLLECTION_GOODS_URL = {"http://api.ds.lingshi.cccwei.com/" +
            "?cid=759713&uid=", "&tms=20160420091221&sig=950747e124711761&wssig=fb3077064657dc53&" +
            "os_type=3&version=22&channel_name=feibo&srv=2805&fav_id=", "&type=", "&opt=1"};


    public static final String[] RESET_PASSWORD_URL = {"http://api.ds.lingshi.cccwei.com/?cid=759713" +
            "&uid=0&tms=20160420113237&sig=e821043a493e3b29&wssig=09aaa10ca487d72b&os_type" +
            "=3&version=22&channel_name=feibo&srv=2102&mobi_num=", "&pwd=", "&msg_code=", "&opt=2"};
    // 淘宝订单URL
    public static final String TAOBAO_ORDER_URL = "https://login.m.taobao.com/login.htm?tpl_redirect_url=" +
            "http%3A%2F%2Fh5.m.taobao.com%2Fmlapp%2Fmytaobao.html%23mlapp-mytaobao";
    // 优惠券url
    public static final String COUPON_URL = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160421190419&sig=a9d30b7a659677be&" +
            "wssig=f23ba7b7fde760e0&os_type=3&version=20&channel_name=wandoujia&srv=2903";
    // 优惠券使用url
    public static final String COUPON_USE_URL = "http://ds.lingshi.cccwei.com/api.php?apptype=0&srv=2910&&cid=10002&uid=0&tms=20150721190147&" +
            "sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=12";

    // 领取优惠券url
    public static final String GET_COUPON_URL = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160421190050&sig=4f2151303af390dd&" +
            "wssig=a2811f4aa03b08c7&os_type=3&version=20&channel_name=wandoujia&srv=3005&coupnum=";
    // 根据手机号码--获取验证码
    public static final String BIND_PHONE_NUM_URL = "http://testapi.ds.lingshi.cccwei.com/api/Tpl/default/Login/bind.html";

    // 绑定手机号码
    public static final String[] BIND_URL = {"http://api.ds.lingshi.cccwei.com/?cid=760272&uid=","&tms=20160422231220&sig=2e2897ff1aec32ce&wssig=34bfaa4e324afdf3&os_type=3&version=20&channel_name=wandoujia&srv=2102&mobi_num=","&pwd=","&msg_code=","&opt=3"};

    // 获取新浪微博信息的url
    public static final String[] WEIBO_INFO = {"http://api.ds.lingshi.cccwei.com/?cid=760272&uid=0&tms=20160423084400&sig=b67496107e6cd81f&wssig=df6263bffd9f0c3a&os_type=3&version=20&channel_name=wandoujia&srv=1101&pf_type=3&openid=","&nickname=","&icon=null"};


    public static final String[] ORDER_URL = {"http://api.ds.lingshi.cccwei.com/?cid=759713&" +
            "uid=", "&tms=20160421151848&sig=31ea1ddfe2f48d06&wssig=96626302acab06ad&os_type=3&version=22&channel_name=feibo" +
            "&srv=2704&pg_cur=", "&pg_size=", "&type=", "&since_id=0"};

    public static final String[] CART_URL = {"http://api.ds.lingshi.cccwei.com/?cid=759713&uid=", "&tms=20160421220732" +
            "&sig=b821e071df4d600a&wssig=a81cbdd4c8d4251e&os_type=3&version=22&channel_name=feibo&srv="};

    /**
     * 删除购物车中的商品
     */
    public static final String[] CART_DELETE_UTL = {"http://api.ds.lingshi.cccwei.com/?cid=759713&" +
            "uid=", "&tms=20160423211628&sig=e1c8b80a01b9522b&wssig=b7a89da36aa677e5&os_type=3&version=22&" +
            "channel_name=feibo&srv=2603&ids="};
    /**
     *
     http://api.ds.lingshi.cccwei.com/?cid=759713&uid=181772&tms=20160423223526&sig=502b215001dbdc5d
     &wssig=294b0494ce39ec07&os_type=3&version=22&channel_name=feibo&srv=2603&ids=303828
     */

    /**
     * 详情页添加购物车
     */

    public static final String[] DES_ADD_CART_URL = {"http://api.ds.lingshi.cccwei.com/?cid=759713&" +
            "uid=", "&tms=20160423220017&sig=cea7affaf81b4126&wssig=07a5588823c2ac57&os_type=3" +
            "&version=22&channel_name=feibo&srv=2601&goods_id=8356&kind_id=12&subkind_id=3651&num=1"};

    /**
     * 购物车修改数量
     */

    public static final String[] ADD_CART_URL = {"http://api.ds.lingshi.cccwei.com/?cid=759713" +
            "&uid=", "&tms=20160423221823&sig=247e7e1e53f601dd&wssig=4230c702a98defa5&os_type=3&" +
            "version=22&channel_name=feibo&srv=2602&item_id=", "&num="};
}
