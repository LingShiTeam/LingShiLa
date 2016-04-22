package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.util.List;

/**
 * Created by lanmang on 2016/4/20.
 */
public class CollSpecialBean {

    /**
     * rs_code : 1000
     * data : {"count":2,"items":[{"id":532,"desc":"说到日本，我们首先想到的是富士山，或者樱花，再或者就是北海道，要是美食的话我们估计就会想到它的寿司，还有精致的日本料理，其实不然，日本也是一个美食国度，对待美食也是极其讲究，其实日本还有一类好吃的东西，日本零食，据说天下妹子无人能挡其诱惑，今天小喵为大家搜罗了那些资深吃货去日本必买的零食清单，看看你都有加入购物车吗？","title":"帮你挑丨跟着味蕾去旅行-日本篇","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/821/21/1/8e15b41b4ae692441b8928495cb95821.jpeg","img_w":612,"img_h":306},"hotindex":2483,"share_num":4},{"id":573,"desc":"薯片就是零食世界最最重要的一部分！前生土豆更是人见人爱的大众情人！虽然含淀粉易发胖、油炸易上火，还被成为垃圾食品，但是还是有辣么多的吃货对其前赴后继、欲罢不能！每当一片片松脆可口的口感在嘴巴里爆炸，就仿佛点燃了所有的味蕾，完全抗拒不了的爽感有木有！一整包躺沙发上抓着吃风味更佳！！小喵带你尝尝各地不同风味的薯片，毕竟吃饱了才有力气减肥呦~~","title":"帮你挑|\u201c薯\u201d一\u201c薯\u201d，你最爱哪个国家的薯片？","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f98/98/8/11cd256d61062114ab846c47ace56f98.jpeg","img_w":612,"img_h":306},"hotindex":997,"share_num":0}]}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * count : 2
     * items : [{"id":532,"desc":"说到日本，我们首先想到的是富士山，或者樱花，再或者就是北海道，要是美食的话我们估计就会想到它的寿司，还有精致的日本料理，其实不然，日本也是一个美食国度，对待美食也是极其讲究，其实日本还有一类好吃的东西，日本零食，据说天下妹子无人能挡其诱惑，今天小喵为大家搜罗了那些资深吃货去日本必买的零食清单，看看你都有加入购物车吗？","title":"帮你挑丨跟着味蕾去旅行-日本篇","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/821/21/1/8e15b41b4ae692441b8928495cb95821.jpeg","img_w":612,"img_h":306},"hotindex":2483,"share_num":4},{"id":573,"desc":"薯片就是零食世界最最重要的一部分！前生土豆更是人见人爱的大众情人！虽然含淀粉易发胖、油炸易上火，还被成为垃圾食品，但是还是有辣么多的吃货对其前赴后继、欲罢不能！每当一片片松脆可口的口感在嘴巴里爆炸，就仿佛点燃了所有的味蕾，完全抗拒不了的爽感有木有！一整包躺沙发上抓着吃风味更佳！！小喵带你尝尝各地不同风味的薯片，毕竟吃饱了才有力气减肥呦~~","title":"帮你挑|\u201c薯\u201d一\u201c薯\u201d，你最爱哪个国家的薯片？","img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/f98/98/8/11cd256d61062114ab846c47ace56f98.jpeg","img_w":612,"img_h":306},"hotindex":997,"share_num":0}]
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
         * id : 532
         * desc : 说到日本，我们首先想到的是富士山，或者樱花，再或者就是北海道，要是美食的话我们估计就会想到它的寿司，还有精致的日本料理，其实不然，日本也是一个美食国度，对待美食也是极其讲究，其实日本还有一类好吃的东西，日本零食，据说天下妹子无人能挡其诱惑，今天小喵为大家搜罗了那些资深吃货去日本必买的零食清单，看看你都有加入购物车吗？
         * title : 帮你挑丨跟着味蕾去旅行-日本篇
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/821/21/1/8e15b41b4ae692441b8928495cb95821.jpeg","img_w":612,"img_h":306}
         * hotindex : 2483
         * share_num : 4
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
            private int id;
            private String desc;
            private String title;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/821/21/1/8e15b41b4ae692441b8928495cb95821.jpeg
             * img_w : 612
             * img_h : 306
             */

            private ImgEntity img;
            private int hotindex;
            private int share_num;

            private boolean isShowDelete;

            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public boolean isShowDelete() {
                return isShowDelete;
            }

            public void setShowDelete(boolean showDelete) {
                isShowDelete = showDelete;
            }

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

            public ImgEntity getImg() {
                return img;
            }

            public void setImg(ImgEntity img) {
                this.img = img;
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
