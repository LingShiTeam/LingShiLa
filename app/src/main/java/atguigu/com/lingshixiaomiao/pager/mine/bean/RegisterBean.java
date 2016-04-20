package atguigu.com.lingshixiaomiao.pager.mine.bean;

/**
 * Created by lanmang on 2016/4/18.
 */
public class RegisterBean {

    @Override
    public String toString() {
        return "RegisterBean{" +
                "rs_code=" + rs_code +
                ", data=" + data +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * rs_code : 1000
     * data : {"code":0}
     * rs_msg : success
     */

    private int rs_code;
    /**
     * code : 0
     */

    private DataEntity data;
    private String rs_msg;

    public int getRs_code() {
        return rs_code;
    }

    public void setRs_code(int rs_code) {
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
                    "code=" + code +
                    ", url='" + url + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", mobi_num='" + mobi_num + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", uid='" + uid + '\'' +
                    ", wskey='" + wskey + '\'' +
                    '}';
        }

        private int code;
        private String url;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String avatar;
        private String mobi_num;
        private String nickname;
        private String uid;
        private String wskey;

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setMobi_num(String mobi_num) {
            this.mobi_num = mobi_num;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setWskey(String wskey) {
            this.wskey = wskey;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getMobi_num() {
            return mobi_num;
        }

        public String getNickname() {
            return nickname;
        }

        public String getUid() {
            return uid;
        }

        public String getWskey() {
            return wskey;
        }
    }
}
