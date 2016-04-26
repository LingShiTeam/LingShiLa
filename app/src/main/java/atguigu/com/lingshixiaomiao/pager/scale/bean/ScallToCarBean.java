package atguigu.com.lingshixiaomiao.pager.scale.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25 0025.
 */
public class ScallToCarBean {

    /**
     * active_info : {"status":0}
     * collect_status : 0
     * desc : 这里的零食看了好想吃
     * guide_info : {"open_type":0,"real_id":0,"real_url":""}
     * guide_type : 0
     * id : 8265
     * img : {"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/9f2/f2/2/a7884dd6a4cb77ea728f18362805c9f2.jpeg","img_w":640}
     * kinds : [{"id":12,"kinds":[{"id":3297,"price":{"current":17.5,"prime":29},"surplus_num":16,"title":"原味"}],"title":"口味"}]
     * salestitle :
     * status : 0
     * title : [台湾雅麦香传] 话梅夹心棒棒糖100g
     * url : http://ds.lingshi.cccwei.com/api.php?apptype=0&srv=2506&goods_id=8265&cid=10002&uid=182064&tms=20150721190147&sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=22
     * xm_say :
     */

    private DataEntity data;
    /**
     * data : {"active_info":{"status":0},"collect_status":0,"desc":"这里的零食看了好想吃","guide_info":{"open_type":0,"real_id":0,"real_url":""},"guide_type":0,"id":8265,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/9f2/f2/2/a7884dd6a4cb77ea728f18362805c9f2.jpeg","img_w":640},"kinds":[{"id":12,"kinds":[{"id":3297,"price":{"current":17.5,"prime":29},"surplus_num":16,"title":"原味"}],"title":"口味"}],"salestitle":"","status":0,"title":"[台湾雅麦香传] 话梅夹心棒棒糖100g","url":"http://ds.lingshi.cccwei.com/api.php?apptype=0&srv=2506&goods_id=8265&cid=10002&uid=182064&tms=20150721190147&sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=22","xm_say":""}
     * rs_code : 1000
     * rs_msg : success
     */

    private String rs_code;
    private String rs_msg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getRs_code() {
        return rs_code;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public static class DataEntity {
        /**
         * status : 0
         */

        private ActiveInfoEntity active_info;
        private int collect_status;
        private String desc;
        /**
         * open_type : 0
         * real_id : 0
         * real_url :
         */

        private GuideInfoEntity guide_info;
        private int guide_type;
        private int id;
        /**
         * img_h : 640
         * img_url : http://img.lingshi.cccwei.com/lingshi/9f2/f2/2/a7884dd6a4cb77ea728f18362805c9f2.jpeg
         * img_w : 640
         */

        private ImgEntity img;
        private String salestitle;
        private int status;
        private String title;
        private String url;
        private String xm_say;
        /**
         * id : 12
         * kinds : [{"id":3297,"price":{"current":17.5,"prime":29},"surplus_num":16,"title":"原味"}]
         * title : 口味
         */

        private List<KindsEntitys> kindss;

        public void setActive_info(ActiveInfoEntity active_info) {
            this.active_info = active_info;
        }

        public void setCollect_status(int collect_status) {
            this.collect_status = collect_status;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setGuide_info(GuideInfoEntity guide_info) {
            this.guide_info = guide_info;
        }

        public void setGuide_type(int guide_type) {
            this.guide_type = guide_type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImg(ImgEntity img) {
            this.img = img;
        }

        public void setSalestitle(String salestitle) {
            this.salestitle = salestitle;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setXm_say(String xm_say) {
            this.xm_say = xm_say;
        }

        public void setKindss(List<KindsEntitys> kindss) {
            this.kindss = kindss;
        }

        public ActiveInfoEntity getActive_info() {
            return active_info;
        }

        public int getCollect_status() {
            return collect_status;
        }

        public String getDesc() {
            return desc;
        }

        public GuideInfoEntity getGuide_info() {
            return guide_info;
        }

        public int getGuide_type() {
            return guide_type;
        }

        public int getId() {
            return id;
        }

        public ImgEntity getImg() {
            return img;
        }

        public String getSalestitle() {
            return salestitle;
        }

        public int getStatus() {
            return status;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getXm_say() {
            return xm_say;
        }

        public List<KindsEntitys> getKindss() {
            return kindss;
        }

        public static class ActiveInfoEntity {
            private int status;

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStatus() {
                return status;
            }
        }

        public static class GuideInfoEntity {
            private int open_type;
            private int real_id;
            private String real_url;

            public void setOpen_type(int open_type) {
                this.open_type = open_type;
            }

            public void setReal_id(int real_id) {
                this.real_id = real_id;
            }

            public void setReal_url(String real_url) {
                this.real_url = real_url;
            }

            public int getOpen_type() {
                return open_type;
            }

            public int getReal_id() {
                return real_id;
            }

            public String getReal_url() {
                return real_url;
            }
        }

        public static class ImgEntity {
            private int img_h;
            private String img_url;
            private int img_w;

            public void setImg_h(int img_h) {
                this.img_h = img_h;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setImg_w(int img_w) {
                this.img_w = img_w;
            }

            public int getImg_h() {
                return img_h;
            }

            public String getImg_url() {
                return img_url;
            }

            public int getImg_w() {
                return img_w;
            }
        }

        public static class KindsEntitys {
            private int id;
            private String title;
            /**
             * id : 3297
             * price : {"current":17.5,"prime":29}
             * surplus_num : 16
             * title : 原味
             */

            private List<KindsEntity> kinds;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setKinds(List<KindsEntity> kinds) {
                this.kinds = kinds;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public List<KindsEntity> getKinds() {
                return kinds;
            }

            public static class KindsEntity {
                private int id;
                /**
                 * current : 17.5
                 * prime : 29
                 */

                private PriceEntity price;
                private int surplus_num;
                private String title;

                public void setId(int id) {
                    this.id = id;
                }

                public void setPrice(PriceEntity price) {
                    this.price = price;
                }

                public void setSurplus_num(int surplus_num) {
                    this.surplus_num = surplus_num;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getId() {
                    return id;
                }

                public PriceEntity getPrice() {
                    return price;
                }

                public int getSurplus_num() {
                    return surplus_num;
                }

                public String getTitle() {
                    return title;
                }

                public static class PriceEntity {
                    private double current;
                    private double prime;

                    public void setCurrent(double current) {
                        this.current = current;
                    }

                    public void setPrime(int prime) {
                        this.prime = prime;
                    }

                    public double getCurrent() {
                        return current;
                    }

                    public double getPrime() {
                        return prime;
                    }
                }
            }
        }
    }
}
