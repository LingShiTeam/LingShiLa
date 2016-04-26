package atguigu.com.lingshixiaomiao.pager.mine.bean;

/**
 * Created by Liu_haiwei on 2016/4/22.
 * 领取优惠券的实体类
 */
public class GetCouponBean {

    /**
     * rs_code : 6003
     * rs_msg : 领取失败，请稍后尝试
     */

    private String rs_code;
    private String rs_msg;

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public String getRs_code() {
        return rs_code;
    }

    public String getRs_msg() {
        return rs_msg;
    }
}
