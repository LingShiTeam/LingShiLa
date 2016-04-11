package atguigu.com.lingshixiaomiao.pager.mine.bean;

/**
 * Created by lanmang on 2016/4/11.
 * 更新
 */
public class UpdateBean {

    @Override
    public String toString() {
        return "UpdateBean{" +
                "rs_code='" + rs_code + '\'' +
                ", data=" + data +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * rs_code : 1000
     * data : {"title":"新版本上线，更新提示","desc":"特卖页面全新改版，更有特卖预告让你挖宝，更多惊喜，立即更新体验吧！","upd_url":"http://dl.lingshi.cccwei.com/feibo_2.3.3.apk"}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * title : 新版本上线，更新提示
     * desc : 特卖页面全新改版，更有特卖预告让你挖宝，更多惊喜，立即更新体验吧！
     * upd_url : http://dl.lingshi.cccwei.com/feibo_2.3.3.apk
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "title='" + title + '\'' +
                    ", desc='" + desc + '\'' +
                    ", upd_url='" + upd_url + '\'' +
                    '}';
        }

        private String title;
        private String desc;
        private String upd_url;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setUpd_url(String upd_url) {
            this.upd_url = upd_url;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getUpd_url() {
            return upd_url;
        }
    }
}
