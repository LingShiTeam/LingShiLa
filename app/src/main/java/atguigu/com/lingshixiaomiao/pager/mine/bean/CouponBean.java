package atguigu.com.lingshixiaomiao.pager.mine.bean;

/**
 * Created by Liu_haiwei on 2016/4/21.
 * 优惠券实体类
 */
public class CouponBean {

    /**
     * rs_code : 1000
     * data : {"status":0}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * status : 0
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
        private int status;

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }
}
