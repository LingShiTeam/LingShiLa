package atguigu.com.lingshixiaomiao.pager.mine.bean;

/**
 * Created by lanmang on 2016/4/20.
 */
public class RemoveCollGoodsBean {

    /**
     * rs_code : 1000
     * data : {"count":6}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * count : 6
     */

    private DataEntity data;
    private String rs_msg;

    public String getRs_code() {
        return rs_code;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public static class DataEntity {
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
