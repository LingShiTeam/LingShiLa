package atguigu.com.lingshixiaomiao.pager.home.bean;

import java.util.List;

/**
 * Created by Liu_haiwei on 2016/4/12.
 * 搜索结果的实体类
 */
public class SearchResultBean {


    /**
     * rs_code : 1000
     * data : {"count":4,"items":[{"status":0,"id":9153,"title":"[意大利 劳瑞世家]盐味面包棒","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":26,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/a2a/2a/a/6d2cfa157565cc495827c8c38df23a2a.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":0},{"status":0,"id":9156,"title":"[意大利劳瑞世家] 迷迭香面包棒饼干 健康营养","sales_title":"","type":0,"guide_type":0,"sold_num":9,"surplus_num":12,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/3d7/d7/7/b481ce6c5875aab2e0375e17aba453d7.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":11,"sub_classify_id":0},{"status":0,"id":9163,"title":"[意大利 劳瑞世家]蒜香味面包棒","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":25,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/d0d/0d/d/eb9e410a369d98995eb53df09a0aed0d.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":0},{"status":0,"id":9167,"title":"[意大利劳瑞世家] 芝麻味面包棒","sales_title":"意大利进口芝麻味面包棒 营养早餐","type":0,"guide_type":0,"sold_num":2,"surplus_num":20,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/403/03/3/66faef78ed5925a4ab659b5233c16403.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":4,"sub_classify_id":0}]}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * count : 4
     * items : [{"status":0,"id":9153,"title":"[意大利 劳瑞世家]盐味面包棒","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":26,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/a2a/2a/a/6d2cfa157565cc495827c8c38df23a2a.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":0},{"status":0,"id":9156,"title":"[意大利劳瑞世家] 迷迭香面包棒饼干 健康营养","sales_title":"","type":0,"guide_type":0,"sold_num":9,"surplus_num":12,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/3d7/d7/7/b481ce6c5875aab2e0375e17aba453d7.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":11,"sub_classify_id":0},{"status":0,"id":9163,"title":"[意大利 劳瑞世家]蒜香味面包棒","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":25,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/d0d/0d/d/eb9e410a369d98995eb53df09a0aed0d.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":0},{"status":0,"id":9167,"title":"[意大利劳瑞世家] 芝麻味面包棒","sales_title":"意大利进口芝麻味面包棒 营养早餐","type":0,"guide_type":0,"sold_num":2,"surplus_num":20,"price":{"current":19.9,"prime":24.3},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/403/03/3/66faef78ed5925a4ab659b5233c16403.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":4,"sub_classify_id":0}]
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
        private int count;
        /**
         * status : 0
         * id : 9153
         * title : [意大利 劳瑞世家]盐味面包棒
         * sales_title :
         * type : 0
         * guide_type : 0
         * sold_num : 1
         * surplus_num : 26
         * price : {"current":19.9,"prime":24.3}
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/a2a/2a/a/6d2cfa157565cc495827c8c38df23a2a.jpeg","img_w":640,"img_h":640}
         * freight : 0
         * time : 0
         * tag : {"title":"","color":0}
         * desc :
         * fav_num : 0
         * sub_classify_id : 0
         */

        private List<ItemsEntity> items;

        public void setCount(int count) {
            this.count = count;
        }

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
        }

        public int getCount() {
            return count;
        }

        public List<ItemsEntity> getItems() {
            return items;
        }

        public static class ItemsEntity {
            private int status;
            private int id;
            private String title;
            private String sales_title;
            private int type;
            private int guide_type;
            private int sold_num;
            private int surplus_num;
            /**
             * current : 19.9
             * prime : 24.3
             */

            private PriceEntity price;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/a2a/2a/a/6d2cfa157565cc495827c8c38df23a2a.jpeg
             * img_w : 640
             * img_h : 640
             */

            private ImgEntity img;
            private int freight;
            private int time;
            /**
             * title :
             * color : 0
             */

            private TagEntity tag;
            private String desc;
            private int fav_num;
            private int sub_classify_id;

            public void setStatus(int status) {
                this.status = status;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setSales_title(String sales_title) {
                this.sales_title = sales_title;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setGuide_type(int guide_type) {
                this.guide_type = guide_type;
            }

            public void setSold_num(int sold_num) {
                this.sold_num = sold_num;
            }

            public void setSurplus_num(int surplus_num) {
                this.surplus_num = surplus_num;
            }

            public void setPrice(PriceEntity price) {
                this.price = price;
            }

            public void setImg(ImgEntity img) {
                this.img = img;
            }

            public void setFreight(int freight) {
                this.freight = freight;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public void setTag(TagEntity tag) {
                this.tag = tag;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setFav_num(int fav_num) {
                this.fav_num = fav_num;
            }

            public void setSub_classify_id(int sub_classify_id) {
                this.sub_classify_id = sub_classify_id;
            }

            public int getStatus() {
                return status;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getSales_title() {
                return sales_title;
            }

            public int getType() {
                return type;
            }

            public int getGuide_type() {
                return guide_type;
            }

            public int getSold_num() {
                return sold_num;
            }

            public int getSurplus_num() {
                return surplus_num;
            }

            public PriceEntity getPrice() {
                return price;
            }

            public ImgEntity getImg() {
                return img;
            }

            public int getFreight() {
                return freight;
            }

            public int getTime() {
                return time;
            }

            public TagEntity getTag() {
                return tag;
            }

            public String getDesc() {
                return desc;
            }

            public int getFav_num() {
                return fav_num;
            }

            public int getSub_classify_id() {
                return sub_classify_id;
            }

            public static class PriceEntity {
                private double current;
                private double prime;

                public void setCurrent(double current) {
                    this.current = current;
                }

                public void setPrime(double prime) {
                    this.prime = prime;
                }

                public double getCurrent() {
                    return current;
                }

                public double getPrime() {
                    return prime;
                }
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

            public static class TagEntity {
                private String title;
                private int color;

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setColor(int color) {
                    this.color = color;
                }

                public String getTitle() {
                    return title;
                }

                public int getColor() {
                    return color;
                }
            }
        }
    }
}
