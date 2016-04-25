package atguigu.com.lingshixiaomiao.pager.home.bean;

/**
 * Created by Liu_haiwei on 2016/4/24.
 * 支付宝支付信息的实体类
 */
public class AlpayInfoBean {

    /**
     * rs_code : 1000
     * data : {"order_sn":"aF0424952793982267","type":0,"pay_info":"partner=\"2088611281802746\"&seller_id=\"feibobomail@126.com\"&out_trade_no=\"F0424952793982267\"&subject=\"小喵的订单\"&body=\"零食小喵商城订单\"&total_fee=\"59.9\"&notify_url=\"http://api.ds.lingshi.cccwei.com/Pay/notifyUrlAliPay\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&show_url=\"m.alipay.com\"&sign=\"fYXY7fqlD0mAl1Y2lVJWeH7Jt91LhMFi0CfGjfNHwul9czvrbe60g4wica5EyYbqSj308ljaUQyNhPZ3negSNszVvxEUvqaP%2BVYTv08jVqzmFo73WAkVzGniCUK%2FBtRHCBdWltPcc1HIipYs%2FclLo%2Fq5B2TkCkosDOfxuX1UYGc%3D\"&sign_type=\"RSA\""}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * order_sn : aF0424952793982267
     * type : 0
     * pay_info : partner="2088611281802746"&seller_id="feibobomail@126.com"&out_trade_no="F0424952793982267"&subject="小喵的订单"&body="零食小喵商城订单"&total_fee="59.9"&notify_url="http://api.ds.lingshi.cccwei.com/Pay/notifyUrlAliPay"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&show_url="m.alipay.com"&sign="fYXY7fqlD0mAl1Y2lVJWeH7Jt91LhMFi0CfGjfNHwul9czvrbe60g4wica5EyYbqSj308ljaUQyNhPZ3negSNszVvxEUvqaP%2BVYTv08jVqzmFo73WAkVzGniCUK%2FBtRHCBdWltPcc1HIipYs%2FclLo%2Fq5B2TkCkosDOfxuX1UYGc%3D"&sign_type="RSA"
     */

    private DataEntity data;
    private String rs_msg;

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public String getRs_code() {
        return rs_code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public static class DataEntity {
        private String order_sn;
        private int type;
        private String pay_info;

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setPay_info(String pay_info) {
            this.pay_info = pay_info;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public int getType() {
            return type;
        }

        public String getPay_info() {
            return pay_info;
        }
    }
}
