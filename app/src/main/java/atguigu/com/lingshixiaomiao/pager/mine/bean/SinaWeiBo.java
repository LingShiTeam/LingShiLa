package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.io.Serializable;

/**
 * Created by Liu_haiwei on 2016/4/23.
 * 新浪微博信息的实体类
 */
public class SinaWeiBo implements Serializable {

    /**
     * rs_code : 1000
     * data : {"uid":"184164","wskey":"1plyT86aTcDRTXL8","avatar":"http://tp2.sinaimg.cn/1769274625/50/5637074200/1","nickname":"南烟〔印〕","mobi_num":"18235188642"}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * uid : 184164
     * wskey : 1plyT86aTcDRTXL8
     * avatar : http://tp2.sinaimg.cn/1769274625/50/5637074200/1
     * nickname : 南烟〔印〕
     * mobi_num : 18235188642
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
        private String uid;
        private String wskey;
        private String avatar;
        private String nickname;
        private String mobi_num;

        public void setUid(String uid) {
            this.uid = uid;
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

        public void setMobi_num(String mobi_num) {
            this.mobi_num = mobi_num;
        }

        public String getUid() {
            return uid;
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

        public String getMobi_num() {
            return mobi_num;
        }

    }

}
