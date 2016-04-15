package atguigu.com.lingshixiaomiao.pager.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Liu_haiwei on 2016/4/14.
 * home页主数据
 */
public class HomeBean implements Serializable {

    /**
     * rs_code : 1000
     * data : {"topics":[{"id":286,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/631/31/1/8068ef7c1bdf6966146bf6b46eb17631.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"544"}},{"id":254,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/858/58/8/571ac34067e77d865a99cb8923b7f858.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"493"}},{"id":204,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/9a2/a2/2/b489782fe4b86b4328170dce451949a2.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"437"}},{"id":231,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ede/de/e/144c211887c867fd4fdf94d6b0f0bede.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"465"}}],"classifies":[{"id":32,"title":"坚果蜜饯","desc":"坚果/炒货/果脯","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/301/01/1/64b4248c9f9d19c443cacab4a0a20301.png","img_w":96,"img_h":96}},{"id":39,"title":"肉干肉脯","desc":"肉干/肉脯/海味","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/d7a/7a/a/5f07a26e12a1adae1eafa5b39934bd7a.png","img_w":96,"img_h":96}},{"id":40,"title":"饼干膨化","desc":"曲奇/威化饼","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ee4/e4/4/ae93a21ad30c5ba5c3d19ec85149eee4.png","img_w":96,"img_h":96}},{"id":42,"title":"冲调饮料","desc":"果汁/饮料/谷物冲调","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/6dc/dc/c/25e0d9acedf0396ba6d5e1f55814d6dc.png","img_w":96,"img_h":96}},{"id":41,"title":"糖巧","desc":"软糖/棒棒糖/奶糖","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/734/34/4/53049a07a4596d2af3cee9803a849734.png","img_w":99,"img_h":99}},{"id":38,"title":"方便食品","desc":"方便面/休闲小食","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/5fb/fb/b/35fa280a753eb63f4a26a0baee76a5fb.png","img_w":96,"img_h":96}}],"brands_title_big":"品牌特卖","brands_title_sml":"折扣限时抢,每天10:00准时上新","brands":[{"id":576,"title":"坚果大本营","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/cec/ec/c/dd994e21972f61df057f155d60299cec.jpg","img_w":640,"img_h":270},"discount":"3.6折起","provider":"","time":1460772000}],"specials":[{"id":291,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/717/17/7/71d0581030c9de939202131e119b4717.jpg","img_w":255,"img_h":320},"action":{"type":4,"info":"560"}},{"id":294,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f01/01/1/22163806429ef08356194b515573af01.jpg","img_w":384,"img_h":128},"action":{"type":4,"info":"566"}},{"id":241,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/511/11/1/f85a2a21411470a8e65de8cd0e806511.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"472"}},{"id":251,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/357/57/7/9b5ab86333971bf9994f34cc9e4d1357.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"490"}}],"new_title_big":"好吃到爆","new_title_sml":"畅销全网的万人迷"}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * topics : [{"id":286,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/631/31/1/8068ef7c1bdf6966146bf6b46eb17631.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"544"}},{"id":254,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/858/58/8/571ac34067e77d865a99cb8923b7f858.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"493"}},{"id":204,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/9a2/a2/2/b489782fe4b86b4328170dce451949a2.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"437"}},{"id":231,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ede/de/e/144c211887c867fd4fdf94d6b0f0bede.jpg","img_w":640,"img_h":270},"action":{"type":4,"info":"465"}}]
     * classifies : [{"id":32,"title":"坚果蜜饯","desc":"坚果/炒货/果脯","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/301/01/1/64b4248c9f9d19c443cacab4a0a20301.png","img_w":96,"img_h":96}},{"id":39,"title":"肉干肉脯","desc":"肉干/肉脯/海味","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/d7a/7a/a/5f07a26e12a1adae1eafa5b39934bd7a.png","img_w":96,"img_h":96}},{"id":40,"title":"饼干膨化","desc":"曲奇/威化饼","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/ee4/e4/4/ae93a21ad30c5ba5c3d19ec85149eee4.png","img_w":96,"img_h":96}},{"id":42,"title":"冲调饮料","desc":"果汁/饮料/谷物冲调","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/6dc/dc/c/25e0d9acedf0396ba6d5e1f55814d6dc.png","img_w":96,"img_h":96}},{"id":41,"title":"糖巧","desc":"软糖/棒棒糖/奶糖","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/734/34/4/53049a07a4596d2af3cee9803a849734.png","img_w":99,"img_h":99}},{"id":38,"title":"方便食品","desc":"方便面/休闲小食","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/5fb/fb/b/35fa280a753eb63f4a26a0baee76a5fb.png","img_w":96,"img_h":96}}]
     * brands_title_big : 品牌特卖
     * brands_title_sml : 折扣限时抢,每天10:00准时上新
     * brands : [{"id":576,"title":"坚果大本营","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/cec/ec/c/dd994e21972f61df057f155d60299cec.jpg","img_w":640,"img_h":270},"discount":"3.6折起","provider":"","time":1460772000}]
     * specials : [{"id":291,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/717/17/7/71d0581030c9de939202131e119b4717.jpg","img_w":255,"img_h":320},"action":{"type":4,"info":"560"}},{"id":294,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f01/01/1/22163806429ef08356194b515573af01.jpg","img_w":384,"img_h":128},"action":{"type":4,"info":"566"}},{"id":241,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/511/11/1/f85a2a21411470a8e65de8cd0e806511.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"472"}},{"id":251,"title":"","desc":"","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/357/57/7/9b5ab86333971bf9994f34cc9e4d1357.jpg","img_w":192,"img_h":192},"action":{"type":4,"info":"490"}}]
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
        private String brands_title_big;
        private String brands_title_sml;
        private String new_title_big;
        private String new_title_sml;
        /**
         * id : 286
         * title :
         * desc :
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/631/31/1/8068ef7c1bdf6966146bf6b46eb17631.jpg","img_w":640,"img_h":270}
         * action : {"type":4,"info":"544"}
         */

        private List<TopicsEntity> topics;
        /**
         * id : 32
         * title : 坚果蜜饯
         * desc : 坚果/炒货/果脯
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/301/01/1/64b4248c9f9d19c443cacab4a0a20301.png","img_w":96,"img_h":96}
         */

        private List<ClassifiesEntity> classifies;
        /**
         * id : 576
         * title : 坚果大本营
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/cec/ec/c/dd994e21972f61df057f155d60299cec.jpg","img_w":640,"img_h":270}
         * discount : 3.6折起
         * provider :
         * time : 1460772000
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

        public void setClassifies(List<ClassifiesEntity> classifies) {
            this.classifies = classifies;
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

        public List<ClassifiesEntity> getClassifies() {
            return classifies;
        }

        public List<BrandsEntity> getBrands() {
            return brands;
        }

        public List<SpecialsEntity> getSpecials() {
            return specials;
        }

        public static class TopicsEntity {
            private int id;
            private String title;
            private String desc;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/631/31/1/8068ef7c1bdf6966146bf6b46eb17631.jpg
             * img_w : 640
             * img_h : 270
             */

            private ImgEntity img;
            /**
             * type : 4
             * info : 544
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

        public static class ClassifiesEntity implements Serializable {
            private int id;
            private String title;
            private String desc;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/301/01/1/64b4248c9f9d19c443cacab4a0a20301.png
             * img_w : 96
             * img_h : 96
             */

            private ImgEntity img;

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

            public static class ImgEntity {
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

        public static class BrandsEntity {
            private int id;
            private String title;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/cec/ec/c/dd994e21972f61df057f155d60299cec.jpg
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
