package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.util.List;

/**
 * Created by lanmang on 2016/4/21.
 */
public class OrderBean {

    /**
     * rs_code : 1000
     * data : {"count":1,"items":[{"type":7,"order_sn":"bF0412912274415307","freight":8,"num":1,"final_sum":17.5,"name":"小喵自营商品","single":{"id":0,"goods_id":9343,"goods_title":"[台湾宏亚] 77手工扁桃仁巧克力杯","state":0,"num":1,"surplus_num":0,"kinds":"口味:扁桃仁","price":{"current":9.5,"prime":28},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f4e/4e/e/ce4a70b6bf82f67c93ad3ecb7355af4e.jpeg","img_w":640,"img_h":640}},"multi":[]}]}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * count : 1
     * items : [{"type":7,"order_sn":"bF0412912274415307","freight":8,"num":1,"final_sum":17.5,"name":"小喵自营商品","single":{"id":0,"goods_id":9343,"goods_title":"[台湾宏亚] 77手工扁桃仁巧克力杯","state":0,"num":1,"surplus_num":0,"kinds":"口味:扁桃仁","price":{"current":9.5,"prime":28},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f4e/4e/e/ce4a70b6bf82f67c93ad3ecb7355af4e.jpeg","img_w":640,"img_h":640}},"multi":[]}]
     */

    private DataEntity data;
    private String rs_msg;

    public String getRs_code() {
        return rs_code;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
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
         * type : 7
         * order_sn : bF0412912274415307
         * freight : 8
         * num : 1
         * final_sum : 17.5
         * name : 小喵自营商品
         * single : {"id":0,"goods_id":9343,"goods_title":"[台湾宏亚] 77手工扁桃仁巧克力杯","state":0,"num":1,"surplus_num":0,"kinds":"口味:扁桃仁","price":{"current":9.5,"prime":28},"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f4e/4e/e/ce4a70b6bf82f67c93ad3ecb7355af4e.jpeg","img_w":640,"img_h":640}}
         * multi : []
         */

        private List<ItemsEntity> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemsEntity> getItems() {
            return items;
        }

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
        }

        public static class ItemsEntity {
            private int type;
            private String order_sn;
            private double freight;
            private int num;
            private double final_sum;
            private String name;
            /**
             * id : 0
             * goods_id : 9343
             * goods_title : [台湾宏亚] 77手工扁桃仁巧克力杯
             * state : 0
             * num : 1
             * surplus_num : 0
             * kinds : 口味:扁桃仁
             * price : {"current":9.5,"prime":28}
             * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/f4e/4e/e/ce4a70b6bf82f67c93ad3ecb7355af4e.jpeg","img_w":640,"img_h":640}
             */

            private SingleEntity single;
            private List<?> multi;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public double getFreight() {
                return freight;
            }

            public void setFreight(double freight) {
                this.freight = freight;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getFinal_sum() {
                return final_sum;
            }

            public void setFinal_sum(double final_sum) {
                this.final_sum = final_sum;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public SingleEntity getSingle() {
                return single;
            }

            public void setSingle(SingleEntity single) {
                this.single = single;
            }

            public List<?> getMulti() {
                return multi;
            }

            public void setMulti(List<?> multi) {
                this.multi = multi;
            }

            public static class SingleEntity {
                private int id;
                private int goods_id;
                private String goods_title;
                private int state;
                private int num;
                private int surplus_num;
                private String kinds;
                /**
                 * current : 9.5
                 * prime : 28
                 */

                private PriceEntity price;
                /**
                 * img_url : http://img.lingshi.cccwei.com/lingshi/f4e/4e/e/ce4a70b6bf82f67c93ad3ecb7355af4e.jpeg
                 * img_w : 640
                 * img_h : 640
                 */

                private ImgEntity img;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

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

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getSurplus_num() {
                    return surplus_num;
                }

                public void setSurplus_num(int surplus_num) {
                    this.surplus_num = surplus_num;
                }

                public String getKinds() {
                    return kinds;
                }

                public void setKinds(String kinds) {
                    this.kinds = kinds;
                }

                public PriceEntity getPrice() {
                    return price;
                }

                public void setPrice(PriceEntity price) {
                    this.price = price;
                }

                public ImgEntity getImg() {
                    return img;
                }

                public void setImg(ImgEntity img) {
                    this.img = img;
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

                public static class ImgEntity {
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
    }
}
