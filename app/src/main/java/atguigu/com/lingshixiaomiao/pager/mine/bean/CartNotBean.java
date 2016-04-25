package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.util.List;

/**
 * Created by lanmang on 2016/4/22.
 */
public class CartNotBean {


    /**
     * count : 1
     * itemss : [{"goods_id":8308,"goods_title":"[农心] 韩国进口辛拉面杯装65g （此批次保质期到5月16日）","id":303828,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/b58/58/8/ffdcc70eabe68e73dc2763a173466b58.jpeg","img_w":640},"kinds":"口味 : 辛拉面","num":4,"price":{"current":6.9,"prime":12},"state":2,"surplus_num":0}]
     */

    private DataEntity data;
    /**
     * data : {"count":1,"itemss":[{"goods_id":8308,"goods_title":"[农心] 韩国进口辛拉面杯装65g （此批次保质期到5月16日）","id":303828,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/b58/58/8/ffdcc70eabe68e73dc2763a173466b58.jpeg","img_w":640},"kinds":"口味 : 辛拉面","num":4,"price":{"current":6.9,"prime":12},"state":2,"surplus_num":0}]}
     * rs_code : 1000
     * rs_msg : success
     */

    private String rs_code;
    private String rs_msg;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getRs_code() {
        return rs_code;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public static class DataEntity {
        private int count;
        /**
         * goods_id : 8308
         * goods_title : [农心] 韩国进口辛拉面杯装65g （此批次保质期到5月16日）
         * id : 303828
         * img : {"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/b58/58/8/ffdcc70eabe68e73dc2763a173466b58.jpeg","img_w":640}
         * kinds : 口味 : 辛拉面
         * num : 4
         * price : {"current":6.9,"prime":12}
         * state : 2
         * surplus_num : 0
         */

        private List<ItemssEntity> itemss;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemssEntity> getItemss() {
            return itemss;
        }

        public void setItemss(List<ItemssEntity> itemss) {
            this.itemss = itemss;
        }

        public static class ItemssEntity {
            private int goods_id;
            private String goods_title;
            private int id;
            /**
             * img_h : 640
             * img_url : http://img.lingshi.cccwei.com/lingshi/b58/58/8/ffdcc70eabe68e73dc2763a173466b58.jpeg
             * img_w : 640
             */

            private ImgEntity img;
            private String kinds;
            private int num;
            /**
             * current : 6.9
             * prime : 12
             */

            private PriceEntity price;
            private int state;
            private int surplus_num;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_title() {
                return goods_title;
            }

            public void setGoods_title(String goods_title) {
                this.goods_title = goods_title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public ImgEntity getImg() {
                return img;
            }

            public void setImg(ImgEntity img) {
                this.img = img;
            }

            public String getKinds() {
                return kinds;
            }

            public void setKinds(String kinds) {
                this.kinds = kinds;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public PriceEntity getPrice() {
                return price;
            }

            public void setPrice(PriceEntity price) {
                this.price = price;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getSurplus_num() {
                return surplus_num;
            }

            public void setSurplus_num(int surplus_num) {
                this.surplus_num = surplus_num;
            }

            public static class ImgEntity {
                private int img_h;
                private String img_url;
                private int img_w;

                public int getImg_h() {
                    return img_h;
                }

                public void setImg_h(int img_h) {
                    this.img_h = img_h;
                }

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
            }

            public static class PriceEntity {
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
