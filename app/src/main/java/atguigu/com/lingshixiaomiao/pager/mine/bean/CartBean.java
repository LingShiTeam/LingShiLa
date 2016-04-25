package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lanmang on 2016/4/22.
 */
public class CartBean implements Serializable{

    @Override
    public String toString() {
        return "CartBean{" +
                "data=" + data +
                ", rs_code='" + rs_code + '\'' +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * count : 1
     * itemss : [{"activity":{},"freight":2.5,"id":4,"items":[{"goods_id":9344,"goods_title":"[台湾宏亚] 77手工松露巧克力杯","id":305331,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/652/52/2/39ffee02432c88526cab10923b39e652.jpeg","img_w":640},"kinds":"口味 : 松露","num":1,"price":{"current":9.5,"prime":28.6},"state":0,"surplus_num":39},{"goods_id":9122,"goods_title":"[日本明治] 抹茶口味夹心雪吻巧克力 浓厚细腻","id":305329,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e58/58/8/00fa9823c2e0ec53f2d2c784c2e55e58.jpeg","img_w":640},"kinds":"口味 : 抹茶味62g","num":2,"price":{"current":14.8,"prime":25.9},"state":0,"surplus_num":114},{"goods_id":8742,"goods_title":"[瑞士TOBLERONE] 三角扁桃仁蜂蜜牛奶巧克力100g","id":305328,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/895/95/5/1b6c09290568d178f196aa9a91e80895.jpeg","img_w":640},"kinds":"口味 : 蜂蜜牛奶巧克力","num":4,"price":{"current":69.9,"prime":24.9},"state":0,"surplus_num":712},{"goods_id":8982,"goods_title":"小鳄鱼系列-小鳄鱼椒盐酥脆饼干80g","id":303985,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/1a0/a0/0/c5ec43ecf00ed753c21a4fe54d5d21a0.jpeg","img_w":640},"kinds":"口味 : 小鳄鱼椒盐酥脆饼干","num":4,"price":{"current":15.9,"prime":28.8},"state":0,"surplus_num":3},{"goods_id":8970,"goods_title":"[泰国7coin] 泰式香辣牛肉风味方便面350g","id":303827,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/a10/10/0/dfa2ce695f6ebf5c270cdf5eda673a10.jpeg","img_w":640},"kinds":"口味 : 香辣牛肉味","num":4,"price":{"current":22.8,"prime":25.9},"state":0,"surplus_num":6}],"name":"小喵自营商品","note":"","num":15,"sum_price":473.5}]
     */

    private DataEntity data;
    /**
     * data : {"count":1,"itemss":[{"activity":{},"freight":2.5,"id":4,"items":[{"goods_id":9344,"goods_title":"[台湾宏亚] 77手工松露巧克力杯","id":305331,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/652/52/2/39ffee02432c88526cab10923b39e652.jpeg","img_w":640},"kinds":"口味 : 松露","num":1,"price":{"current":9.5,"prime":28.6},"state":0,"surplus_num":39},{"goods_id":9122,"goods_title":"[日本明治] 抹茶口味夹心雪吻巧克力 浓厚细腻","id":305329,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e58/58/8/00fa9823c2e0ec53f2d2c784c2e55e58.jpeg","img_w":640},"kinds":"口味 : 抹茶味62g","num":2,"price":{"current":14.8,"prime":25.9},"state":0,"surplus_num":114},{"goods_id":8742,"goods_title":"[瑞士TOBLERONE] 三角扁桃仁蜂蜜牛奶巧克力100g","id":305328,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/895/95/5/1b6c09290568d178f196aa9a91e80895.jpeg","img_w":640},"kinds":"口味 : 蜂蜜牛奶巧克力","num":4,"price":{"current":69.9,"prime":24.9},"state":0,"surplus_num":712},{"goods_id":8982,"goods_title":"小鳄鱼系列-小鳄鱼椒盐酥脆饼干80g","id":303985,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/1a0/a0/0/c5ec43ecf00ed753c21a4fe54d5d21a0.jpeg","img_w":640},"kinds":"口味 : 小鳄鱼椒盐酥脆饼干","num":4,"price":{"current":15.9,"prime":28.8},"state":0,"surplus_num":3},{"goods_id":8970,"goods_title":"[泰国7coin] 泰式香辣牛肉风味方便面350g","id":303827,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/a10/10/0/dfa2ce695f6ebf5c270cdf5eda673a10.jpeg","img_w":640},"kinds":"口味 : 香辣牛肉味","num":4,"price":{"current":22.8,"prime":25.9},"state":0,"surplus_num":6}],"name":"小喵自营商品","note":"","num":15,"sum_price":473.5}]}
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

    public static class DataEntity implements Serializable{

        @Override
        public String toString() {
            return "DataEntity{" +
                    "count=" + count +
                    ", itemss=" + itemss +
                    '}';
        }

        private int count;
        /**
         * activity : {}
         * freight : 2.5
         * id : 4
         * items : [{"goods_id":9344,"goods_title":"[台湾宏亚] 77手工松露巧克力杯","id":305331,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/652/52/2/39ffee02432c88526cab10923b39e652.jpeg","img_w":640},"kinds":"口味 : 松露","num":1,"price":{"current":9.5,"prime":28.6},"state":0,"surplus_num":39},{"goods_id":9122,"goods_title":"[日本明治] 抹茶口味夹心雪吻巧克力 浓厚细腻","id":305329,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e58/58/8/00fa9823c2e0ec53f2d2c784c2e55e58.jpeg","img_w":640},"kinds":"口味 : 抹茶味62g","num":2,"price":{"current":14.8,"prime":25.9},"state":0,"surplus_num":114},{"goods_id":8742,"goods_title":"[瑞士TOBLERONE] 三角扁桃仁蜂蜜牛奶巧克力100g","id":305328,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/895/95/5/1b6c09290568d178f196aa9a91e80895.jpeg","img_w":640},"kinds":"口味 : 蜂蜜牛奶巧克力","num":4,"price":{"current":69.9,"prime":24.9},"state":0,"surplus_num":712},{"goods_id":8982,"goods_title":"小鳄鱼系列-小鳄鱼椒盐酥脆饼干80g","id":303985,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/1a0/a0/0/c5ec43ecf00ed753c21a4fe54d5d21a0.jpeg","img_w":640},"kinds":"口味 : 小鳄鱼椒盐酥脆饼干","num":4,"price":{"current":15.9,"prime":28.8},"state":0,"surplus_num":3},{"goods_id":8970,"goods_title":"[泰国7coin] 泰式香辣牛肉风味方便面350g","id":303827,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/a10/10/0/dfa2ce695f6ebf5c270cdf5eda673a10.jpeg","img_w":640},"kinds":"口味 : 香辣牛肉味","num":4,"price":{"current":22.8,"prime":25.9},"state":0,"surplus_num":6}]
         * name : 小喵自营商品
         * note :
         * num : 15
         * sum_price : 473.5
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

        public static class ItemssEntity implements Serializable{

            @Override
            public String toString() {
                return "ItemssEntity{" +
                        "freight=" + freight +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", note='" + note + '\'' +
                        ", num=" + num +
                        ", sum_price=" + sum_price +
                        ", checkAll=" + checkAll +
                        ", items=" + items +
                        '}';
            }

            private double freight;
            private int id;
            private String name;
            private String note;
            private int num;
            private double sum_price;
            private boolean checkAll;

            public boolean isCheckAll() {
                return checkAll;
            }

            public void setCheckAll(boolean checkAll) {
                this.checkAll = checkAll;
            }

            /**
             * goods_id : 9344
             * goods_title : [台湾宏亚] 77手工松露巧克力杯
             * id : 305331
             * img : {"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/652/52/2/39ffee02432c88526cab10923b39e652.jpeg","img_w":640}
             * kinds : 口味 : 松露
             * num : 1
             * price : {"current":9.5,"prime":28.6}
             * state : 0
             * surplus_num : 39
             */

            private List<ItemsEntity> items;

            public double getFreight() {
                return freight;
            }

            public void setFreight(double freight) {
                this.freight = freight;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getSum_price() {
                return sum_price;
            }

            public void setSum_price(double sum_price) {
                this.sum_price = sum_price;
            }

            public List<ItemsEntity> getItems() {
                return items;
            }

            public void setItems(List<ItemsEntity> items) {
                this.items = items;
            }

            public static class ItemsEntity implements Serializable{


                @Override
                public String toString() {
                    return "ItemsEntity{" +
                            "goods_id=" + goods_id +
                            ", goods_title='" + goods_title + '\'' +
                            ", id=" + id +
                            ", img=" + img +
                            ", kinds='" + kinds + '\'' +
                            ", num=" + num +
                            ", check=" + check +
                            ", price=" + price +
                            ", state=" + state +
                            ", surplus_num=" + surplus_num +
                            '}';
                }

                private int goods_id;
                private String goods_title;
                private int id;
                /**
                 * img_h : 640
                 * img_url : http://img.lingshi.cccwei.com/lingshi/652/52/2/39ffee02432c88526cab10923b39e652.jpeg
                 * img_w : 640
                 */

                private ImgEntity img;
                private String kinds;
                private int num;
                /**
                 * current : 9.5
                 * prime : 28.6
                 */

                private boolean check;

                public boolean isCheck() {
                    return check;
                }

                public void setCheck(boolean check) {
                    this.check = check;
                }

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

                public static class ImgEntity implements Serializable{

                    @Override
                    public String toString() {
                        return "ImgEntity{" +
                                "img_h=" + img_h +
                                ", img_url='" + img_url + '\'' +
                                ", img_w=" + img_w +
                                '}';
                    }

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

                public static class PriceEntity implements Serializable{

                    @Override
                    public String toString() {
                        return "PriceEntity{" +
                                "current=" + current +
                                ", prime=" + prime +
                                '}';
                    }

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
