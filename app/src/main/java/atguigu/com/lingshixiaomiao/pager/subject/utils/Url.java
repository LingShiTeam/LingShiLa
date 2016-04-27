package atguigu.com.lingshixiaomiao.pager.subject.utils;

/**
 * Created by CheungJhonny on 2016/4/10.
 * 专题详情页面url的实体类
 */
public class Url {

    /**
     * 服务器地址
     */
    public static final String BASE_URL = "http://api.ds.lingshi.cccwei.com";
    /**
     * 专题详情页面 中 上部 Gridview 的地址
     */
    public static final String SBUJECT_GRIDVIEW = BASE_URL + "/?cid=109&uid=0&tms=20160410060415&sig=a77a1ceeb1d6f92e&wssig=302cd1b3ee977063&os_type=3&version=18&channel_name=feibo&srv=2401";

    /**
     * 专题详情页面 下部 listview 的地址
     */
    public static final String SBUJECT_LISTVIEW = BASE_URL + "/?cid=109&uid=0&tms=20160410060418&sig=956d0a32d3aaeba2&wssig=90c3a304ae42d95a&os_type=3&version=18&channel_name=feibo&srv=2405&pg_cur=1&pg_size=20&since_id=0";
    //    http://api.ds.lingshi.cccwei.com/?cid=109&uid=0&tms=20160410060418&sig=956d0a32d3aaeba2&wssig=90c3a304ae42d95a&os_type=3&version=18&channel_name=feibo&srv=2405&pg_cur=1&pg_size=20&since_id=0

    /**
     * 上拉加载更多的数据
     */
    public static final String SBUJECT_LISTVIEW_MORE = BASE_URL + "/?cid=109&uid=0&tms=20160419082303&sig=a9f56f4239a96c75&wssig=c0459aa4b19a733e&os_type=3&version=20&channel_name=wandoujia&srv=2405&";

    /**
     * 专题详情页面 顶部top 点击后的数据地址
     */
    public static final String SUBJECT_TOP_DATA = BASE_URL + "/?cid=109&uid=0&tms=20160420005812&sig=167038ac68b0ad50&wssig=6cba698c1e140169&os_type=3&version=20&channel_name=wandoujia&srv=2407&pg_cur=";
    /**
     * 专题详情页面 listview 商品详情的地址
     */
    public static final String SUBJECT_DETAILS = BASE_URL + "/?cid=109&uid=";
    public static final String SUBJECT_DETAILS_END = "&tms=20160423015946&sig=0c93723eb210e544&wssig=56a5c360937774f6&os_type=3&version=20&channel_name=wandoujia&srv=2408&subject_id=";
    /**
     * 商品列表的美味详情详情页面的请求地址
     */
    public static final String SUBJECT_DELICACY_DETAILS_START = BASE_URL + "/?cid=109&uid=";
    public static final String SUBJECT_DELICACY_DETAILS_END = "&tms=20160422075904&sig=db983a1c7248e8b9&wssig=85d2a8009f1972e6&os_type=3&version=20&channel_name=wandoujia&srv=2505&goods_id=";
    /**
     * 收藏按钮 的地址(包括的收藏状态)
     */
    public static final String SUBJECT_COLLECTOR_STATRT = BASE_URL + "/?cid=109&uid=";
    public static final String SUBJECT_COLLECTOR_MIDDLE = "&tms=20160423034018&sig=fee60a16fb4deb75&wssig=5e853a63aabf465f&os_type=3&version=20&channel_name=wandoujia&srv=2805&fav_id=";
    public static final String SUBJECT_COLLECTOR_END = "&type=1&opt=";


    /**
     * 添加购物车的请求网址 (专题详情)
     */
    public static final String SUBJECT_ADDCART_START = BASE_URL + "/?cid=109&uid=";
    public static final String SUBJECT_ADDCART_MIDDLE1 = "&tms=20160427083323&sig=d213f498d76b90ea&wssig=dd161666f74972ce&os_type=3&version=20&channel_name=wandoujia&srv=2601&goods_id=";
    public static final String SUBJECT_ADDCART_MIDDLE2 = "&kind_id=";
    public static final String SUBJECT_ADDCART_MIDDLE3 = "&subkind_id=";
    public static final String SUBJECT_ADDCART_END = "&num=";

    /**
     * 添加到购物车
     */



}
