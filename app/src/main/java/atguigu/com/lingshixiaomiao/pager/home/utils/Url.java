package atguigu.com.lingshixiaomiao.pager.home.utils;

/**
 * Created by lanmang on 2016/4/8.
 */
public class Url {
    /**
     * 首页头部链接
     */
    public static final String HOME_TOP_URL = "http://api.ds.lingshi.cccwei.com/?cid=" +
            "760294&uid=0&tms=20160406223600&sig=5ad479a0b6bcaff9&wssig=a62fcd9fb5171bca&os_type=3&version=18&channel_name=feibo&srv=2201";
    /**
     * 首页分页数据链接
     */
    public static final String HOME_TOP_PAGE = "http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=20160406224555&sig" +
            "=77fe35c8027c2e4a&wssig=e4fe9113b21617de&os_type=3&version=18&channel_name=feibo&srv=2206&since_id=0;//&pg_cur=1&pg_size=20";
    /**
     * 搜索页面基础数据的链接
     */
    public static final String HOME_SEARCH_BASE = "http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=20160406230339&sig=" +
            "3555e415884a4d66&wssig=41f81e0164c815b3&os_type=3&version=18&channel_name=feibo&srv=2204&since_id=9167&pg_cur=2&pg_size=20&keyword=";
    /**
     * 首页数据+侧滑菜单数据的链接
     */
    public static final String HOME_DATA_BASE = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=0&tms=20160414182156&sig=0a14474e0e2ac484&wssig=" +
            "ebe27f08eec25775&os_type=3&version=20&channel_name=wandoujia&srv=2201";
    /**
     * 侧滑菜单栏详情页面标签的链接
     * http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160415145826&sig=9f18131c921e2758&wssig=b1aae3295371e0f1&os_type=3
     * &version=20&channel_name=wandoujia&srv=2402&classify_id=32
     */
    public static final String MENU_ITEM_TAB_TITLE = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160415145826&sig=9f18131c921e2758&wssig=" +
            "b1aae3295371e0f1&os_type=3&version=20&channel_name=wandoujia&srv=2402&classify_id=";
    /**
     * 侧滑菜单栏详情页面全部
     */
    public static final String MENU_ITEM_TAB_DES_MAIM = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160415140544&sig=c98893f7c3cd52ff&wssig=3334a64360e583c4&os_type=" +
            "3&version=20&channel_name=wandoujia&srv=2406&pg_cur=1&pg_size=20&sub_id=0&parent_id=";//32&since_id=0"

    /**
     * 侧滑菜单栏详情页面
     */
    public static final String MENU_ITEM_TAB_DES = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160415140544&sig=c98893f7c3cd52ff&wssig=3334a64360e583c4&os_type=" +
            "3&version=20&channel_name=wandoujia&srv=2406&pg_cur=1&pg_size=20&sub_id=";//0&parent_id=32&since_id=0";

}
