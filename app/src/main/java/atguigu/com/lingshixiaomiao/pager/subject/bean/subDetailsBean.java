package atguigu.com.lingshixiaomiao.pager.subject.bean;

/**
 * Created by CheungJhonny on 2016/4/23.
 *
 *  专题页面 专题详情页面的实体类
 */
public class SubDetailsBean {

    /**
     * rs_code : 1000
     * data : {"id":578,"desc":"瑞士三角牌巧克力（Toblerone)来源于瑞士，中文名字源于其特有的三角造型，象征瑞士阿尔卑斯的马特洪峰（Matterhorn）。微博推荐的世界9大顶级巧克力品牌瑞士三角排名第一，也是各大航空公司、免税店必选长脸巧克力，更是那部经典的老友记中JoeY的最爱！第一次拿到三角巧克力是在小喵家试吃时拿到的，第一个问题就是，从哪儿开始下嘴\u2026\u2026（此处有小岳岳配音\u201c我的天呐\u201d）！今天小喵做了详细的功课（还要感谢我们的设计美女的协助），对瑞士三角周边做了各种了解，赶紧过来嘚瑟一下下！","title":"帮你挑|你真的吃对了瑞士三角巧克力吗？","collect_status":0,"hotindex":287,"share_num":695,"web_url":"http://ds.lingshi.cccwei.com/api.php?active=0&apptype=0&srv=2414&subject_id=578&cid=10002&uid=0&tms=20150721190147&sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=12","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/e38/38/8/90cc6064a4c7e8aceadc8ff49c49de38.jpeg","img_w":612,"img_h":306}}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * id : 578
     * desc : 瑞士三角牌巧克力（Toblerone)来源于瑞士，中文名字源于其特有的三角造型，象征瑞士阿尔卑斯的马特洪峰（Matterhorn）。微博推荐的世界9大顶级巧克力品牌瑞士三角排名第一，也是各大航空公司、免税店必选长脸巧克力，更是那部经典的老友记中JoeY的最爱！第一次拿到三角巧克力是在小喵家试吃时拿到的，第一个问题就是，从哪儿开始下嘴……（此处有小岳岳配音“我的天呐”）！今天小喵做了详细的功课（还要感谢我们的设计美女的协助），对瑞士三角周边做了各种了解，赶紧过来嘚瑟一下下！
     * title : 帮你挑|你真的吃对了瑞士三角巧克力吗？
     * collect_status : 0
     * hotindex : 287
     * share_num : 695
     * web_url : http://ds.lingshi.cccwei.com/api.php?active=0&apptype=0&srv=2414&subject_id=578&cid=10002&uid=0&tms=20150721190147&sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=12
     * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/e38/38/8/90cc6064a4c7e8aceadc8ff49c49de38.jpeg","img_w":612,"img_h":306}
     */

    private DataBean data;
    private String rs_msg;

    public String getRs_code() {
        return rs_code;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public static class DataBean {
        private int id;
        private String desc;
        private String title;
        private int collect_status;
        private int hotindex;
        private int share_num;
        private String web_url;
        /**
         * img_url : http://img.lingshi.cccwei.com/lingshi/e38/38/8/90cc6064a4c7e8aceadc8ff49c49de38.jpeg
         * img_w : 612
         * img_h : 306
         */

        private ImgBean img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_status() {
            return collect_status;
        }

        public void setCollect_status(int collect_status) {
            this.collect_status = collect_status;
        }

        public int getHotindex() {
            return hotindex;
        }

        public void setHotindex(int hotindex) {
            this.hotindex = hotindex;
        }

        public int getShare_num() {
            return share_num;
        }

        public void setShare_num(int share_num) {
            this.share_num = share_num;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public ImgBean getImg() {
            return img;
        }

        public void setImg(ImgBean img) {
            this.img = img;
        }

        public static class ImgBean {
            private String img_url;
            private int img_w;
            private int img_h;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public int getImg_w() {
                return img_w;
            }

            public void setImg_w(int img_w) {
                this.img_w = img_w;
            }

            public int getImg_h() {
                return img_h;
            }

            public void setImg_h(int img_h) {
                this.img_h = img_h;
            }
        }
    }
}
