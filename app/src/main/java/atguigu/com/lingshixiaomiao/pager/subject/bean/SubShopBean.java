package atguigu.com.lingshixiaomiao.pager.subject.bean;

import java.util.List;

/**
 * Created by CheungJhonny on 2016/4/22.
 *
 * 顶部shop 的列表shop单个的实体类
 */
public class SubShopBean {


    /**
     * rs_code : 1000
     * data : {"id":9398,"url":"http://ds.lingshi.cccwei.com/api.php?apptype=0&srv=2506&goods_id=9398&cid=10002&uid=184164&tms=20150721190147&sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=20","collect_status":0,"desc":"这里的零食看了好想吃","title":"[好巴食] 川味山椒味豆腐干95g袋","salestitle":"","xm_say":"","status":0,"guide_type":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/374/74/4/356614ad461987d266cc2e8df02ad374.jpeg","img_w":640,"img_h":640},"kindss":[{"id":12,"title":"口味","kinds":[{"id":6817,"title":"山椒味","price":{"current":4.2,"prime":7.6},"surplus_num":9}]}],"guide_info":{"open_type":0,"real_url":"","real_id":0},"active_info":{"status":0}}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * id : 9398
     * url : http://ds.lingshi.cccwei.com/api.php?apptype=0&srv=2506&goods_id=9398&cid=10002&uid=184164&tms=20150721190147&sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=20
     * collect_status : 0
     * desc : 这里的零食看了好想吃
     * title : [好巴食] 川味山椒味豆腐干95g袋
     * salestitle :
     * xm_say :
     * status : 0
     * guide_type : 0
     * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/374/74/4/356614ad461987d266cc2e8df02ad374.jpeg","img_w":640,"img_h":640}
     * kindss : [{"id":12,"title":"口味","kinds":[{"id":6817,"title":"山椒味","price":{"current":4.2,"prime":7.6},"surplus_num":9}]}]
     * guide_info : {"open_type":0,"real_url":"","real_id":0}
     * active_info : {"status":0}
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
        private String url;
        private int collect_status;
        private String desc;
        private String title;
        private String salestitle;
        private String xm_say;
        private int status;
        private int guide_type;
        /**
         * img_url : http://img.lingshi.cccwei.com/lingshi/374/74/4/356614ad461987d266cc2e8df02ad374.jpeg
         * img_w : 640
         * img_h : 640
         */

        private ImgBean img;
        /**
         * open_type : 0
         * real_url :
         * real_id : 0
         */

        private GuideInfoBean guide_info;
        /**
         * status : 0
         */

        private ActiveInfoBean active_info;
        /**
         * id : 12
         * title : 口味
         * kinds : [{"id":6817,"title":"山椒味","price":{"current":4.2,"prime":7.6},"surplus_num":9}]
         */

        private List<KindssBean> kindss;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getCollect_status() {
            return collect_status;
        }

        public void setCollect_status(int collect_status) {
            this.collect_status = collect_status;
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

        public String getSalestitle() {
            return salestitle;
        }

        public void setSalestitle(String salestitle) {
            this.salestitle = salestitle;
        }

        public String getXm_say() {
            return xm_say;
        }

        public void setXm_say(String xm_say) {
            this.xm_say = xm_say;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getGuide_type() {
            return guide_type;
        }

        public void setGuide_type(int guide_type) {
            this.guide_type = guide_type;
        }

        public ImgBean getImg() {
            return img;
        }

        public void setImg(ImgBean img) {
            this.img = img;
        }

        public GuideInfoBean getGuide_info() {
            return guide_info;
        }

        public void setGuide_info(GuideInfoBean guide_info) {
            this.guide_info = guide_info;
        }

        public ActiveInfoBean getActive_info() {
            return active_info;
        }

        public void setActive_info(ActiveInfoBean active_info) {
            this.active_info = active_info;
        }

        public List<KindssBean> getKindss() {
            return kindss;
        }

        public void setKindss(List<KindssBean> kindss) {
            this.kindss = kindss;
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

        public static class GuideInfoBean {
            private int open_type;
            private String real_url;
            private int real_id;

            public int getOpen_type() {
                return open_type;
            }

            public void setOpen_type(int open_type) {
                this.open_type = open_type;
            }

            public String getReal_url() {
                return real_url;
            }

            public void setReal_url(String real_url) {
                this.real_url = real_url;
            }

            public int getReal_id() {
                return real_id;
            }

            public void setReal_id(int real_id) {
                this.real_id = real_id;
            }
        }

        public static class ActiveInfoBean {
            private int status;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class KindssBean {
            private int id;
            private String title;
            /**
             * id : 6817
             * title : 山椒味
             * price : {"current":4.2,"prime":7.6}
             * surplus_num : 9
             */

            private List<KindsBean> kinds;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<KindsBean> getKinds() {
                return kinds;
            }

            public void setKinds(List<KindsBean> kinds) {
                this.kinds = kinds;
            }

            public static class KindsBean {
                private int id;
                private String title;
                /**
                 * current : 4.2
                 * prime : 7.6
                 */

                private PriceBean price;
                private int surplus_num;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public PriceBean getPrice() {
                    return price;
                }

                public void setPrice(PriceBean price) {
                    this.price = price;
                }

                public int getSurplus_num() {
                    return surplus_num;
                }

                public void setSurplus_num(int surplus_num) {
                    this.surplus_num = surplus_num;
                }

                public static class PriceBean {
                    private double current;
                    private double prime;

                    public double getCurrent() {
                        return current;
                    }

                    public void setCurrent(double current) {
                        this.current = current;
                    }

                    public double getPrime() {
                        return prime;
                    }

                    public void setPrime(double prime) {
                        this.prime = prime;
                    }
                }
            }
        }
    }
}
