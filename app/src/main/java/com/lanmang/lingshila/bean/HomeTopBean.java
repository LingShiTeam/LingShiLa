package com.lanmang.lingshila.bean;

import java.util.List;

/**
 * Created by lanmang on 2016/4/8.
 */
public class HomeTopBean {

    @Override
    public String toString() {
        return "HomeTopBean{" +
                "rs_code='" + rs_code + '\'' +
                ", data=" + data +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * rs_code : 1000
     * data : {"topics":[{"id":297,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/b3a/3a/a/fac1cc95e702c90b5abafd6d9c651b3a.jpg","img_w":640,"img_h":270},"action":{"type":8,"info":"20"}},{"id":249,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ab4/b4/4/fef4e5d88ebd251864cccadda837bab4.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"487"}},{"id":231,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ede/de/e/144c211887c867fd4fdf94d6b0f0bede.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"465"}},{"id":225,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/7fd/fd/d/f80ba3a3569e514d7e8b2f38cb0217fd.png","img_w":640,"img_h":270},"action":{"type":4,"info":"457"}},{"id":214,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/2df/df/f/bf3a6d8d649c6ad6e92a3b8f6d6472df.png","img_w":640,"img_h":270},"action":{"type":4,"info":"448"}}],"brands_title_big":"品牌特卖","brands_title_sml":"折扣限时抢,每天10:00准时上新","brands":[{"id":572,"title":"小鲜肉","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/752/52/2/99568a63e2d4e5751357ec007d080752.jpg","img_w":640,"img_h":270},"discount":"4.5折起","provider":"","time":1460167200}],"specials":[{"id":291,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/717/17/7/71d0581030c9de939202131e119b4717.jpg","img_w":255,"img_h":320},"action":{"type":4,"info":"560"}},{"id":294,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f01/01/1/22163806429ef08356194b515573af01.jpg","img_w":384,"img_h":128},"action":{"type":4,"info":"566"}},{"id":241,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/bb5/b5/5/825e3c51a51524f31c1d956e8d625bb5.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"472"}},{"id":251,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/357/57/7/9b5ab86333971bf9994f34cc9e4d1357.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"490"}}],"new_title_big":"好吃到爆","new_title_sml":"畅销全网的万人迷"}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * topics : [{"id":297,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/b3a/3a/a/fac1cc95e702c90b5abafd6d9c651b3a.jpg","img_w":640,"img_h":270},"action":{"type":8,"info":"20"}},{"id":249,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ab4/b4/4/fef4e5d88ebd251864cccadda837bab4.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"487"}},{"id":231,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ede/de/e/144c211887c867fd4fdf94d6b0f0bede.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"465"}},{"id":225,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/7fd/fd/d/f80ba3a3569e514d7e8b2f38cb0217fd.png","img_w":640,"img_h":270},"action":{"type":4,"info":"457"}},{"id":214,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/2df/df/f/bf3a6d8d649c6ad6e92a3b8f6d6472df.png","img_w":640,"img_h":270},"action":{"type":4,"info":"448"}}]
     * brands_title_big : 品牌特卖
     * brands_title_sml : 折扣限时抢,每天10:00准时上新
     * brands : [{"id":572,"title":"小鲜肉","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/752/52/2/99568a63e2d4e5751357ec007d080752.jpg","img_w":640,"img_h":270},"discount":"4.5折起","provider":"","time":1460167200}]
     * specials : [{"id":291,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/717/17/7/71d0581030c9de939202131e119b4717.jpg","img_w":255,"img_h":320},"action":{"type":4,"info":"560"}},{"id":294,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f01/01/1/22163806429ef08356194b515573af01.jpg","img_w":384,"img_h":128},"action":{"type":4,"info":"566"}},{"id":241,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/bb5/b5/5/825e3c51a51524f31c1d956e8d625bb5.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"472"}},{"id":251,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/357/57/7/9b5ab86333971bf9994f34cc9e4d1357.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"490"}}]
     * new_title_big : 好吃到爆
     * new_title_sml : 畅销全网的万人迷
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
                    "brands_title_big='" + brands_title_big + '\'' +
                    ", brands_title_sml='" + brands_title_sml + '\'' +
                    ", new_title_big='" + new_title_big + '\'' +
                    ", new_title_sml='" + new_title_sml + '\'' +
                    ", topics=" + topics +
                    ", brands=" + brands +
                    ", specials=" + specials +
                    '}';
        }

        private String brands_title_big;
        private String brands_title_sml;
        private String new_title_big;
        private String new_title_sml;
        /**
         * id : 297
         * title :
         * desc :
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/b3a/3a/a/fac1cc95e702c90b5abafd6d9c651b3a.jpg","img_w":640,"img_h":270}
         * action : {"type":8,"info":"20"}
         */

        private List<TopicsEntity> topics;
        /**
         * id : 572
         * title : 小鲜肉
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/752/52/2/99568a63e2d4e5751357ec007d080752.jpg","img_w":640,"img_h":270}
         * discount : 4.5折起
         * provider :
         * time : 1460167200
         */

        private List<BrandsEntity> brands;
        /**
         * id : 291
         * title :
         * desc :
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/717/17/7/71d0581030c9de939202131e119b4717.jpg","img_w":255,"img_h":320}
         * action : {"type":4,"info":"560"}
         */

        private List<SpecialsEntity> specials;

        public void setBrands_title_big(String brands_title_big) {
            this.brands_title_big = brands_title_big;
        }

        public void setBrands_title_sml(String brands_title_sml) {
            this.brands_title_sml = brands_title_sml;
        }

        public void setNew_title_big(String new_title_big) {
            this.new_title_big = new_title_big;
        }

        public void setNew_title_sml(String new_title_sml) {
            this.new_title_sml = new_title_sml;
        }

        public void setTopics(List<TopicsEntity> topics) {
            this.topics = topics;
        }

        public void setBrands(List<BrandsEntity> brands) {
            this.brands = brands;
        }

        public void setSpecials(List<SpecialsEntity> specials) {
            this.specials = specials;
        }

        public String getBrands_title_big() {
            return brands_title_big;
        }

        public String getBrands_title_sml() {
            return brands_title_sml;
        }

        public String getNew_title_big() {
            return new_title_big;
        }

        public String getNew_title_sml() {
            return new_title_sml;
        }

        public List<TopicsEntity> getTopics() {
            return topics;
        }

        public List<BrandsEntity> getBrands() {
            return brands;
        }

        public List<SpecialsEntity> getSpecials() {
            return specials;
        }

        public static class TopicsEntity {

            @Override
            public String toString() {
                return "TopicsEntity{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", desc='" + desc + '\'' +
                        ", img=" + img +
                        ", action=" + action +
                        '}';
            }

            private int id;
            private String title;
            private String desc;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/b3a/3a/a/fac1cc95e702c90b5abafd6d9c651b3a.jpg
             * img_w : 640
             * img_h : 270
             */

            private ImgEntity img;
            /**
             * type : 8
             * info : 20
             */

            private ActionEntity action;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setImg(ImgEntity img) {
                this.img = img;
            }

            public void setAction(ActionEntity action) {
                this.action = action;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getDesc() {
                return desc;
            }

            public ImgEntity getImg() {
                return img;
            }

            public ActionEntity getAction() {
                return action;
            }

            public static class ImgEntity {

                @Override
                public String toString() {
                    return "ImgEntity{" +
                            "img_url='" + img_url + '\'' +
                            ", img_w=" + img_w +
                            ", img_h=" + img_h +
                            '}';
                }

                private String img_url;
                private int img_w;
                private int img_h;

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public void setImg_w(int img_w) {
                    this.img_w = img_w;
                }

                public void setImg_h(int img_h) {
                    this.img_h = img_h;
                }

                public String getImg_url() {
                    return img_url;
                }

                public int getImg_w() {
                    return img_w;
                }

                public int getImg_h() {
                    return img_h;
                }
            }

            public static class ActionEntity {

                @Override
                public String toString() {
                    return "ActionEntity{" +
                            "type=" + type +
                            ", info='" + info + '\'' +
                            '}';
                }

                private int type;
                private String info;

                public void setType(int type) {
                    this.type = type;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public int getType() {
                    return type;
                }

                public String getInfo() {
                    return info;
                }
            }
        }

        public static class BrandsEntity {

            @Override
            public String toString() {
                return "BrandsEntity{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", img=" + img +
                        ", discount='" + discount + '\'' +
                        ", provider='" + provider + '\'' +
                        ", time=" + time +
                        '}';
            }

            private int id;
            private String title;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/752/52/2/99568a63e2d4e5751357ec007d080752.jpg
             * img_w : 640
             * img_h : 270
             */

            private ImgEntity img;
            private String discount;
            private String provider;
            private int time;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setImg(ImgEntity img) {
                this.img = img;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public void setProvider(String provider) {
                this.provider = provider;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public ImgEntity getImg() {
                return img;
            }

            public String getDiscount() {
                return discount;
            }

            public String getProvider() {
                return provider;
            }

            public int getTime() {
                return time;
            }

            public static class ImgEntity {

                @Override
                public String toString() {
                    return "ImgEntity{" +
                            "img_url='" + img_url + '\'' +
                            ", img_w=" + img_w +
                            ", img_h=" + img_h +
                            '}';
                }

                private String img_url;
                private int img_w;
                private int img_h;

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public void setImg_w(int img_w) {
                    this.img_w = img_w;
                }

                public void setImg_h(int img_h) {
                    this.img_h = img_h;
                }

                public String getImg_url() {
                    return img_url;
                }

                public int getImg_w() {
                    return img_w;
                }

                public int getImg_h() {
                    return img_h;
                }
            }
        }

        public static class SpecialsEntity {

            @Override
            public String toString() {
                return "SpecialsEntity{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", desc='" + desc + '\'' +
                        ", img=" + img +
                        ", action=" + action +
                        '}';
            }

            private int id;
            private String title;
            private String desc;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/717/17/7/71d0581030c9de939202131e119b4717.jpg
             * img_w : 255
             * img_h : 320
             */

            private ImgEntity img;
            /**
             * type : 4
             * info : 560
             */

            private ActionEntity action;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setImg(ImgEntity img) {
                this.img = img;
            }

            public void setAction(ActionEntity action) {
                this.action = action;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getDesc() {
                return desc;
            }

            public ImgEntity getImg() {
                return img;
            }

            public ActionEntity getAction() {
                return action;
            }

            public static class ImgEntity {

                @Override
                public String toString() {
                    return "ImgEntity{" +
                            "img_url='" + img_url + '\'' +
                            ", img_w=" + img_w +
                            ", img_h=" + img_h +
                            '}';
                }

                private String img_url;
                private int img_w;
                private int img_h;

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public void setImg_w(int img_w) {
                    this.img_w = img_w;
                }

                public void setImg_h(int img_h) {
                    this.img_h = img_h;
                }

                public String getImg_url() {
                    return img_url;
                }

                public int getImg_w() {
                    return img_w;
                }

                public int getImg_h() {
                    return img_h;
                }
            }

            public static class ActionEntity {

                @Override
                public String toString() {
                    return "ActionEntity{" +
                            "type=" + type +
                            ", info='" + info + '\'' +
                            '}';
                }

                private int type;
                private String info;

                public void setType(int type) {
                    this.type = type;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public int getType() {
                    return type;
                }

                public String getInfo() {
                    return info;
                }
            }
        }
    }
}
