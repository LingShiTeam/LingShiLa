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
    public static final String SUBJECT_TOP_DATA = BASE_URL + "/?cid=109&uid=0&tms=20160420005812&sig=167038ac68b0ad50&wssig=6cba698c1e140169&os_type=3&version=20&channel_name=wandoujia&srv=2407&pg_cur=1&pg_size=20&subject_";

}
