package atguigu.com.lingshixiaomiao.pager.scale.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21 0021.
 */
public class ScaleCarBean {

    @Override
    public String toString() {
        return "ScaleCarBean{" +
                "data=" + data +
                ", rs_code='" + rs_code + '\'' +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * count : 1
     * itemss : [{"activity":{},"freight":2.5,"id":4,"items":[{"goods_id":8542,"goods_title":"[美国噗噗脆] POPCORNERS干酪胡椒味玉米片 142g","id":313893,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d8d/8d/d/f970ced3d81bc4b86c3b71e07ef96d8d.jpeg","img_w":640},"kinds":"口味 : 干酪胡椒味","num":1,"price":{"current":16.5,"prime":29.5},"state":0,"surplus_num":11},{"goods_id":9057,"goods_title":"[雅麦香传]原味牛轧糖 香浓味道","id":313892,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d66/66/6/83851a8c7ad0c9daf7e744e772e04d66.jpeg","img_w":640},"kinds":"口味 : 原味","num":1,"price":{"current":25,"prime":45},"state":0,"surplus_num":12},{"goods_id":9053,"goods_title":"[美国贝蒂]浆果味水果卷糖","id":313890,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/71f/1f/f/5f2f8fa6ec1143404af751f019b9c71f.jpeg","img_w":640},"kinds":"口味 : 浆果味","num":1,"price":{"current":21,"prime":46.9},"state":0,"surplus_num":6},{"goods_id":8927,"goods_title":"[台湾妙小象] 拿破仑燕麦棒饼干(原味)150g","id":313889,"img":{"img_h":400,"img_url":"http://img.lingshi.cccwei.com/lingshi/05a/5a/a/eba770e640d6fc9b490b6e2c1444405a.jpeg","img_w":400},"kinds":"口味 : 原味","num":8,"price":{"current":12,"prime":28.9},"state":0,"surplus_num":22},{"goods_id":9333,"goods_title":"[老奶奶] 花生米142g/袋 花生米中的挚爱","id":313732,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/4f4/f4/4/c942c441d37c55221de1c77672ff54f4.jpeg","img_w":640},"kinds":"口味 : 原味","num":1,"price":{"current":5.9,"prime":6.9},"state":0,"surplus_num":134}],"name":"小喵自营商品","note":"","num":12,"sum_price":164.4}]
     */

    private DataEntity data;
    /**
     * data : {"count":1,"itemss":[{"activity":{},"freight":2.5,"id":4,"items":[{"goods_id":8542,"goods_title":"[美国噗噗脆] POPCORNERS干酪胡椒味玉米片 142g","id":313893,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d8d/8d/d/f970ced3d81bc4b86c3b71e07ef96d8d.jpeg","img_w":640},"kinds":"口味 : 干酪胡椒味","num":1,"price":{"current":16.5,"prime":29.5},"state":0,"surplus_num":11},{"goods_id":9057,"goods_title":"[雅麦香传]原味牛轧糖 香浓味道","id":313892,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d66/66/6/83851a8c7ad0c9daf7e744e772e04d66.jpeg","img_w":640},"kinds":"口味 : 原味","num":1,"price":{"current":25,"prime":45},"state":0,"surplus_num":12},{"goods_id":9053,"goods_title":"[美国贝蒂]浆果味水果卷糖","id":313890,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/71f/1f/f/5f2f8fa6ec1143404af751f019b9c71f.jpeg","img_w":640},"kinds":"口味 : 浆果味","num":1,"price":{"current":21,"prime":46.9},"state":0,"surplus_num":6},{"goods_id":8927,"goods_title":"[台湾妙小象] 拿破仑燕麦棒饼干(原味)150g","id":313889,"img":{"img_h":400,"img_url":"http://img.lingshi.cccwei.com/lingshi/05a/5a/a/eba770e640d6fc9b490b6e2c1444405a.jpeg","img_w":400},"kinds":"口味 : 原味","num":8,"price":{"current":12,"prime":28.9},"state":0,"surplus_num":22},{"goods_id":9333,"goods_title":"[老奶奶] 花生米142g/袋 花生米中的挚爱","id":313732,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/4f4/f4/4/c942c441d37c55221de1c77672ff54f4.jpeg","img_w":640},"kinds":"口味 : 原味","num":1,"price":{"current":5.9,"prime":6.9},"state":0,"surplus_num":134}],"name":"小喵自营商品","note":"","num":12,"sum_price":164.4}]}
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
         * items : [{"goods_id":8542,"goods_title":"[美国噗噗脆] POPCORNERS干酪胡椒味玉米片 142g","id":313893,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d8d/8d/d/f970ced3d81bc4b86c3b71e07ef96d8d.jpeg","img_w":640},"kinds":"口味 : 干酪胡椒味","num":1,"price":{"current":16.5,"prime":29.5},"state":0,"surplus_num":11},{"goods_id":9057,"goods_title":"[雅麦香传]原味牛轧糖 香浓味道","id":313892,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d66/66/6/83851a8c7ad0c9daf7e744e772e04d66.jpeg","img_w":640},"kinds":"口味 : 原味","num":1,"price":{"current":25,"prime":45},"state":0,"surplus_num":12},{"goods_id":9053,"goods_title":"[美国贝蒂]浆果味水果卷糖","id":313890,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/71f/1f/f/5f2f8fa6ec1143404af751f019b9c71f.jpeg","img_w":640},"kinds":"口味 : 浆果味","num":1,"price":{"current":21,"prime":46.9},"state":0,"surplus_num":6},{"goods_id":8927,"goods_title":"[台湾妙小象] 拿破仑燕麦棒饼干(原味)150g","id":313889,"img":{"img_h":400,"img_url":"http://img.lingshi.cccwei.com/lingshi/05a/5a/a/eba770e640d6fc9b490b6e2c1444405a.jpeg","img_w":400},"kinds":"口味 : 原味","num":8,"price":{"current":12,"prime":28.9},"state":0,"surplus_num":22},{"goods_id":9333,"goods_title":"[老奶奶] 花生米142g/袋 花生米中的挚爱","id":313732,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/4f4/f4/4/c942c441d37c55221de1c77672ff54f4.jpeg","img_w":640},"kinds":"口味 : 原味","num":1,"price":{"current":5.9,"prime":6.9},"state":0,"surplus_num":134}]
         * name : 小喵自营商品
         * note :
         * num : 12
         * sum_price : 164.4
         */

        private List<ItemssEntity> itemss;

        public void setCount(int count) {
            this.count = count;
        }

        public void setItemss(List<ItemssEntity> itemss) {
            this.itemss = itemss;
        }

        public int getCount() {
            return count;
        }

        public List<ItemssEntity> getItemss() {
            return itemss;
        }

        public static class ItemssEntity {

            @Override
            public String toString() {
                return "ItemssEntity{" +
                        "freight=" + freight +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", note='" + note + '\'' +
                        ", num=" + num +
                        ", sum_price=" + sum_price +
                        ", items=" + items +
                        '}';
            }

            private double freight;
            private int id;
            private String name;
            private String note;
            private int num;
            private double sum_price;
            /**
             * goods_id : 8542
             * goods_title : [美国噗噗脆] POPCORNERS干酪胡椒味玉米片 142g
             * id : 313893
             * img : {"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d8d/8d/d/f970ced3d81bc4b86c3b71e07ef96d8d.jpeg","img_w":640}
             * kinds : 口味 : 干酪胡椒味
             * num : 1
             * price : {"current":16.5,"prime":29.5}
             * state : 0
             * surplus_num : 11
             */

            private List<ItemsEntity> items;

            public void setFreight(double freight) {
                this.freight = freight;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public void setSum_price(double sum_price) {
                this.sum_price = sum_price;
            }

            public void setItems(List<ItemsEntity> items) {
                this.items = items;
            }

            public double getFreight() {
                return freight;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getNote() {
                return note;
            }

            public int getNum() {
                return num;
            }

            public double getSum_price() {
                return sum_price;
            }

            public List<ItemsEntity> getItems() {
                return items;
            }

            public static class ItemsEntity {

                @Override
                public String toString() {
                    return "ItemsEntity{" +
                            "goods_id=" + goods_id +
                            ", goods_title='" + goods_title + '\'' +
                            ", id=" + id +
                            ", img=" + img +
                            ", kinds='" + kinds + '\'' +
                            ", num=" + num +
                            ", price=" + price +
                            ", state=" + state +
                            ", surplus_num=" + surplus_num +
                            '}';
                }

                //记录当前item的cb状态设置是否来自于总计的cb
                private boolean isFromZongJiCb;

                public boolean getIsFromZongJiCb() {
                    return isFromZongJiCb;
                }

                public void setIsFromZongJiCb(boolean isFromZongJiCb) {
                    this.isFromZongJiCb = isFromZongJiCb;
                }

                //记录当前商品数
                private int currentCount;

                public int getCurrentCount() {
                    return this.currentCount;
                }

                public void setCurrentCount(int currentCount) {
                    this.currentCount = currentCount;
                }

                //记录当前商品是否被选中
                private boolean checked;

                public boolean isChecked() {
                    return checked;
                }

                public void setChecked(boolean checked) {
                    this.checked = checked;
                }

                private int goods_id;
                private String goods_title;
                private int id;
                /**
                 * img_h : 640
                 * img_url : http://img.lingshi.cccwei.com/lingshi/d8d/8d/d/f970ced3d81bc4b86c3b71e07ef96d8d.jpeg
                 * img_w : 640
                 */

                private ImgEntity img;
                private String kinds;
                private int num;
                /**
                 * current : 16.5
                 * prime : 29.5
                 */

                private PriceEntity price;
                private int state;
                private int surplus_num;

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public void setGoods_title(String goods_title) {
                    this.goods_title = goods_title;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setImg(ImgEntity img) {
                    this.img = img;
                }

                public void setKinds(String kinds) {
                    this.kinds = kinds;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public void setPrice(PriceEntity price) {
                    this.price = price;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public void setSurplus_num(int surplus_num) {
                    this.surplus_num = surplus_num;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public String getGoods_title() {
                    return goods_title;
                }

                public int getId() {
                    return id;
                }

                public ImgEntity getImg() {
                    return img;
                }

                public String getKinds() {
                    return kinds;
                }

                public int getNum() {
                    return num;
                }

                public PriceEntity getPrice() {
                    return price;
                }

                public int getState() {
                    return state;
                }

                public int getSurplus_num() {
                    return surplus_num;
                }

                public static class ImgEntity {

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

                public static class PriceEntity {

                    @Override
                    public String toString() {
                        return "PriceEntity{" +
                                "current=" + current +
                                ", prime=" + prime +
                                '}';
                    }

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
            }
        }
    }
}
