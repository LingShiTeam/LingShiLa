package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.io.Serializable;

/**
 * Created by lanmang on 2016/4/11.
 * 登录
 */
public class LoginBean implements Serializable {

    @Override
    public String toString() {
        return "LoginBean{" +
                "rs_code='" + rs_code + '\'' +
                ", data=" + data +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * rs_code : 1000
     * data : {"uid":"181772","mobi_num":"18686823149","wskey":"4G1E7sAXHEIrY9HE","avatar":"http://img.lingshi.cccwei.com/lingshi/avatar/2016/3/cee9297d-8d4a-4e19-83d2-231e5c01d03b.png","nickname":"186"}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * uid : 181772
     * mobi_num : 18686823149
     * wskey : 4G1E7sAXHEIrY9HE
     * avatar : http://img.lingshi.cccwei.com/lingshi/avatar/2016/3/cee9297d-8d4a-4e19-83d2-231e5c01d03b.png
     * nickname : 186
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

    public static class DataEntity implements Serializable {

        @Override
        public String toString() {
            return "DataEntity{" +
                    "uid='" + uid + '\'' +
                    ", mobi_num='" + mobi_num + '\'' +
                    ", wskey='" + wskey + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }

        private String uid;
        private String mobi_num;
        private String wskey;
        private String avatar;
        private String nickname;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setMobi_num(String mobi_num) {
            this.mobi_num = mobi_num;
        }

        public void setWskey(String wskey) {
            this.wskey = wskey;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUid() {
            return uid;
        }

        public String getMobi_num() {
            return mobi_num;
        }

        public String getWskey() {
            return wskey;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getNickname() {
            return nickname;
        }
    }
}
