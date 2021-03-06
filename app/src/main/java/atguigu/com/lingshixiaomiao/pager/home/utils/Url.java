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

    /**
     *  多项选择-商品列表URL-第一个(疯抢巧克力)
     */
    public static final String LOVERURL = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160420215705&sig=04b5079414613cb3&wssig=1d9a71b0dd70be0d&os_type=3&version=20&" +
            "channel_name=wandoujia&srv=2407&pg_cur=1&pg_size=20&subject_id=560&since_id=0";

    /**
     *  多项选择-商品列表URL-第二个(两元秒杀)
     */
    public static final String MOVIEURL = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160421111343&sig=04cfeb6d7bbf9f39&wssig=d23c93168a2d4e3e&os_type=3&version=20&" +
            "channel_name=wandoujia&srv=2407&pg_cur=1&pg_size=20&subject_id=566&since_id=0";

    /**
     *  多项选择-商品列表URL-第三个(饿了么)
     */
    public static final String TEAURL = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160421112240&sig=3809f5ba10957e54&wssig=af01744b9b9c257a&os_type=3&version=20&" +
            "channel_name=wandoujia&srv=2407&pg_cur=1&pg_size=20&subject_id=472&since_id=0";

    /**
     *  多项选择-商品列表URL-第四个(热卖单品榜)
     */
    public static final String BEERURL = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160421112447&sig=b81a65c21c2cd52b&wssig=5f7556fb176b4eb0&os_type=3&version=20&" +
            "channel_name=wandoujia&srv=2407&pg_cur=1&pg_size=20&subject_id=490&since_id=0";

    /**
     *  第二个头的数据链接
     */
    public static final String HOMEHEADERTWO = "http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160421114104&sig=390e90e11107830b&wssig=43c2e25690d6863a&os_type=3&version=20&" +
            "channel_name=wandoujia&srv=2205&brand_id=576&pg_cur=1&pg_size=20&since_id=0";

    /**
     * 支付信息的url(items_id/items_items_id)
     */
    public static final String[] PAY_INFO_URL = {"http://api.ds.lingshi.cccwei.com/?cid=760272&uid=","&tms=20160424185336&sig=a29b776ccf97e0c8&wssig=364fcad7fdc36c56&os_type=3&version=20&channel_name=wandoujia&srv=2702&coupon_id=0&poster_ids=&add_id=25702&type=0&notes=%5B%7B%22note%22%3A%22%22%2C%22id%22%3A","%7D%5D&ids="};
}
