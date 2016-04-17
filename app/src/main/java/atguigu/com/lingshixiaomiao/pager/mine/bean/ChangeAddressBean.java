package atguigu.com.lingshixiaomiao.pager.mine.bean;

/**
 * Created by lanmang on 2016/4/14.
 */
public class ChangeAddressBean {

    @Override
    public String toString() {
        return "ChangeAddressBean{" +
                "rs_code='" + rs_code + '\'' +
                ", data=" + data +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * rs_code : 1000
     * data : {"id":24850}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * id : 24850
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "id=" + id +
                    '}';
        }

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
