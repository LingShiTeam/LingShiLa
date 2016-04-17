package atguigu.com.lingshixiaomiao.pager.home.bean;

import java.util.List;

/**
 * Created by Liu_haiwei on 2016/4/16.
 * 菜单详情页面的实体类
 */
public class TabDataBean {

    /**
     * rs_code : 1000
     * data : {"count":20,"items":[{"id":9516,"title":"[赵七]若抢零食枣","sales_title":"","type":0,"guide_type":0,"sold_num":4,"surplus_num":217,"price":{"current":6.9,"prime":15},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/5a7/a7/7/dc0ae99c3848b07804930d11a486e5a7.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":9513,"title":"[赵七]桂圆","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":214,"price":{"current":5.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/1e4/e4/4/6bf3c601f37eef7bbc4503768c9a01e4.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":9515,"title":"[赵七]葡萄干","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":211,"price":{"current":5.9,"prime":9.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/592/92/2/6017c2e4667b86750d086bc2526ba592.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":166},{"id":8949,"title":"[香港U100] 原味梅片22g 西梅制品","sales_title":"","type":0,"guide_type":0,"sold_num":11,"surplus_num":38,"price":{"current":10.9,"prime":17},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/961/61/1/e888aa84c5e2c53a478c892aebddd961.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":6,"sub_classify_id":166},{"id":8947,"title":"[香港U100] 原味梅饼52g","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":34,"price":{"current":12.9,"prime":25},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/264/64/4/2a7b1192691cf7b54e9573b3844a9264.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":9,"sub_classify_id":166},{"id":8911,"title":"[如水] 冰糖杨梅100g","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":29,"price":{"current":9.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/7a0/a0/0/6170540aeadb99dab7a0ff4e64ee57a0.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":3,"sub_classify_id":166},{"id":8928,"title":"[如水] 蜜饯果脯美国西梅小包装90g","sales_title":"","type":0,"guide_type":0,"sold_num":5,"surplus_num":24,"price":{"current":11,"prime":19},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/603/03/3/66e234e139b50035a5c49a5f591e6603.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":8902,"title":"[如水] 铁山楂200g 健康助消化","sales_title":"","type":0,"guide_type":0,"sold_num":14,"surplus_num":22,"price":{"current":9.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/d7d/7d/d/5983e7650e2fe09dafaa56a042b67d7d.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":6,"sub_classify_id":166},{"id":8948,"title":"[香港U100] 炭烧梅饼35g 健胃消食","sales_title":"","type":0,"guide_type":0,"sold_num":3,"surplus_num":21,"price":{"current":12.9,"prime":25},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/068/68/8/9c89e2c1ab8a39e8563c19f7084f0068.jpg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":8896,"title":"[如水] 雪山楂160g","sales_title":"","type":0,"guide_type":0,"sold_num":3,"surplus_num":17,"price":{"current":10.8,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/55e/5e/e/0879acc7de6fccc2f4a6cd479115455e.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":5,"sub_classify_id":166},{"id":9509,"title":"[庄味夫]葡萄干200g","sales_title":"","type":0,"guide_type":0,"sold_num":0,"surplus_num":16,"price":{"current":32.8,"prime":39},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/e37/37/7/12ee3ed95d97adf5c3b449598586ee37.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":166},{"id":8926,"title":"[如水] 过年蜜饯蜜枣150g","sales_title":"","type":0,"guide_type":0,"sold_num":20,"surplus_num":16,"price":{"current":9.9,"prime":17},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/418/18/8/a122d7723991da6d2068e78b3ac0b418.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":4,"sub_classify_id":166},{"id":8903,"title":"[如水] 山楂片180g","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":14,"price":{"current":9.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/dc8/c8/8/a2e948cd17c24662e89ca2dfbff26dc8.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":3,"sub_classify_id":166},{"id":8916,"title":"[如水] 酸甜杏脯100g","sales_title":"","type":0,"guide_type":0,"sold_num":9,"surplus_num":14,"price":{"current":10.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/601/01/1/4127fb8effd8de13e2404816200f0601.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":166},{"id":8356,"title":"[越南闲食一番] 菠萝蜜果干100g","sales_title":"","type":0,"guide_type":0,"sold_num":21,"surplus_num":13,"price":{"current":15.5,"prime":26},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/a51/51/1/40e12dc61e81007a8fe0391dc43e0a51.jpg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":34,"sub_classify_id":166},{"id":8220,"title":"[美国Mariani] 玛莉安妮大粒西梅干283g","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":10,"price":{"current":29.9,"prime":49},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/a6c/6c/c/1e7f472c2b446e952f4dab4b26536a6c.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":9482,"title":"[如水]猕猴桃干蜜饯","sales_title":"","type":0,"guide_type":0,"sold_num":5,"surplus_num":10,"price":{"current":11.9,"prime":22},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/0ef/ef/f/8ec375b3cdff1c9dccc8010527f460ef.jpg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":2,"sub_classify_id":166},{"id":8223,"title":"[美国Mariani] 玛莉安妮半颗蔓越莓干170g","sales_title":"","type":0,"guide_type":0,"sold_num":29,"surplus_num":6,"price":{"current":29.9,"prime":32},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/b24/24/4/ac61b16377d9f8d1dd9df92163144b24.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":55,"sub_classify_id":166},{"id":8218,"title":"美国玛丽安妮去核罐装西梅干 源自天然","sales_title":"","type":0,"guide_type":0,"sold_num":40,"surplus_num":4,"price":{"current":69.9,"prime":69},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/0a0/a0/0/fc3193540f5592a1adae9a8b7aee10a0.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":28,"sub_classify_id":166},{"id":8950,"title":"[香港U100] 紫苏梅片 酸梅子制品","sales_title":"","type":0,"guide_type":0,"sold_num":73,"surplus_num":1,"price":{"current":10.9,"prime":17},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/46d/6d/d/06b7473259418c264b3b9cc8bd3a146d.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":44,"sub_classify_id":166}]}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * count : 20
     * items : [{"id":9516,"title":"[赵七]若抢零食枣","sales_title":"","type":0,"guide_type":0,"sold_num":4,"surplus_num":217,"price":{"current":6.9,"prime":15},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/5a7/a7/7/dc0ae99c3848b07804930d11a486e5a7.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":9513,"title":"[赵七]桂圆","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":214,"price":{"current":5.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/1e4/e4/4/6bf3c601f37eef7bbc4503768c9a01e4.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":9515,"title":"[赵七]葡萄干","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":211,"price":{"current":5.9,"prime":9.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/592/92/2/6017c2e4667b86750d086bc2526ba592.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":166},{"id":8949,"title":"[香港U100] 原味梅片22g 西梅制品","sales_title":"","type":0,"guide_type":0,"sold_num":11,"surplus_num":38,"price":{"current":10.9,"prime":17},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/961/61/1/e888aa84c5e2c53a478c892aebddd961.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":6,"sub_classify_id":166},{"id":8947,"title":"[香港U100] 原味梅饼52g","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":34,"price":{"current":12.9,"prime":25},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/264/64/4/2a7b1192691cf7b54e9573b3844a9264.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":9,"sub_classify_id":166},{"id":8911,"title":"[如水] 冰糖杨梅100g","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":29,"price":{"current":9.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/7a0/a0/0/6170540aeadb99dab7a0ff4e64ee57a0.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":3,"sub_classify_id":166},{"id":8928,"title":"[如水] 蜜饯果脯美国西梅小包装90g","sales_title":"","type":0,"guide_type":0,"sold_num":5,"surplus_num":24,"price":{"current":11,"prime":19},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/603/03/3/66e234e139b50035a5c49a5f591e6603.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":8902,"title":"[如水] 铁山楂200g 健康助消化","sales_title":"","type":0,"guide_type":0,"sold_num":14,"surplus_num":22,"price":{"current":9.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/d7d/7d/d/5983e7650e2fe09dafaa56a042b67d7d.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":6,"sub_classify_id":166},{"id":8948,"title":"[香港U100] 炭烧梅饼35g 健胃消食","sales_title":"","type":0,"guide_type":0,"sold_num":3,"surplus_num":21,"price":{"current":12.9,"prime":25},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/068/68/8/9c89e2c1ab8a39e8563c19f7084f0068.jpg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":8896,"title":"[如水] 雪山楂160g","sales_title":"","type":0,"guide_type":0,"sold_num":3,"surplus_num":17,"price":{"current":10.8,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/55e/5e/e/0879acc7de6fccc2f4a6cd479115455e.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":5,"sub_classify_id":166},{"id":9509,"title":"[庄味夫]葡萄干200g","sales_title":"","type":0,"guide_type":0,"sold_num":0,"surplus_num":16,"price":{"current":32.8,"prime":39},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/e37/37/7/12ee3ed95d97adf5c3b449598586ee37.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":166},{"id":8926,"title":"[如水] 过年蜜饯蜜枣150g","sales_title":"","type":0,"guide_type":0,"sold_num":20,"surplus_num":16,"price":{"current":9.9,"prime":17},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/418/18/8/a122d7723991da6d2068e78b3ac0b418.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":4,"sub_classify_id":166},{"id":8903,"title":"[如水] 山楂片180g","sales_title":"","type":0,"guide_type":0,"sold_num":6,"surplus_num":14,"price":{"current":9.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/dc8/c8/8/a2e948cd17c24662e89ca2dfbff26dc8.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":3,"sub_classify_id":166},{"id":8916,"title":"[如水] 酸甜杏脯100g","sales_title":"","type":0,"guide_type":0,"sold_num":9,"surplus_num":14,"price":{"current":10.9,"prime":12.9},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/601/01/1/4127fb8effd8de13e2404816200f0601.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":0,"sub_classify_id":166},{"id":8356,"title":"[越南闲食一番] 菠萝蜜果干100g","sales_title":"","type":0,"guide_type":0,"sold_num":21,"surplus_num":13,"price":{"current":15.5,"prime":26},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/a51/51/1/40e12dc61e81007a8fe0391dc43e0a51.jpg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":34,"sub_classify_id":166},{"id":8220,"title":"[美国Mariani] 玛莉安妮大粒西梅干283g","sales_title":"","type":0,"guide_type":0,"sold_num":1,"surplus_num":10,"price":{"current":29.9,"prime":49},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/a6c/6c/c/1e7f472c2b446e952f4dab4b26536a6c.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":1,"sub_classify_id":166},{"id":9482,"title":"[如水]猕猴桃干蜜饯","sales_title":"","type":0,"guide_type":0,"sold_num":5,"surplus_num":10,"price":{"current":11.9,"prime":22},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/0ef/ef/f/8ec375b3cdff1c9dccc8010527f460ef.jpg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":2,"sub_classify_id":166},{"id":8223,"title":"[美国Mariani] 玛莉安妮半颗蔓越莓干170g","sales_title":"","type":0,"guide_type":0,"sold_num":29,"surplus_num":6,"price":{"current":29.9,"prime":32},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/b24/24/4/ac61b16377d9f8d1dd9df92163144b24.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":55,"sub_classify_id":166},{"id":8218,"title":"美国玛丽安妮去核罐装西梅干 源自天然","sales_title":"","type":0,"guide_type":0,"sold_num":40,"surplus_num":4,"price":{"current":69.9,"prime":69},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/0a0/a0/0/fc3193540f5592a1adae9a8b7aee10a0.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":28,"sub_classify_id":166},{"id":8950,"title":"[香港U100] 紫苏梅片 酸梅子制品","sales_title":"","type":0,"guide_type":0,"sold_num":73,"surplus_num":1,"price":{"current":10.9,"prime":17},"status":0,"img":{"img_url":"http://img.lingshi.cccwei.com/lingshi/46d/6d/d/06b7473259418c264b3b9cc8bd3a146d.jpeg","img_w":640,"img_h":640},"freight":0,"time":0,"tag":{"title":"","color":0},"desc":"","fav_num":44,"sub_classify_id":166}]
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
         * id : 9516
         * title : [赵七]若抢零食枣
         * sales_title :
         * type : 0
         * guide_type : 0
         * sold_num : 4
         * surplus_num : 217
         * price : {"current":6.9,"prime":15}
         * status : 0
         * img : {"img_url":"http://img.lingshi.cccwei.com/lingshi/5a7/a7/7/dc0ae99c3848b07804930d11a486e5a7.jpeg","img_w":640,"img_h":640}
         * freight : 0
         * time : 0
         * tag : {"title":"","color":0}
         * desc :
         * fav_num : 1
         * sub_classify_id : 166
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
            private String id;
            private String title;
            private String sales_title;
            private String type;
            private String guide_type;
            private String sold_num;
            private String surplus_num;
            /**
             * current : 6.9
             * prime : 15
             */

            private PriceEntity price;
            private String status;
            /**
             * img_url : http://img.lingshi.cccwei.com/lingshi/5a7/a7/7/dc0ae99c3848b07804930d11a486e5a7.jpeg
             * img_w : 640
             * img_h : 640
             */

            private ImgEntity img;
            private String freight;
            private String time;
            /**
             * title :
             * color : 0
             */

            private TagEntity tag;
            private String desc;
            private String fav_num;
            private String sub_classify_id;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setSales_title(String sales_title) {
                this.sales_title = sales_title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setGuide_type(String guide_type) {
                this.guide_type = guide_type;
            }

            public void setSold_num(String sold_num) {
                this.sold_num = sold_num;
            }

            public void setSurplus_num(String surplus_num) {
                this.surplus_num = surplus_num;
            }

            public void setPrice(PriceEntity price) {
                this.price = price;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setImg(ImgEntity img) {
                this.img = img;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public void setTag(TagEntity tag) {
                this.tag = tag;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setFav_num(String fav_num) {
                this.fav_num = fav_num;
            }

            public void setSub_classify_id(String sub_classify_id) {
                this.sub_classify_id = sub_classify_id;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getSales_title() {
                return sales_title;
            }

            public String getType() {
                return type;
            }

            public String getGuide_type() {
                return guide_type;
            }

            public String getSold_num() {
                return sold_num;
            }

            public String getSurplus_num() {
                return surplus_num;
            }

            public PriceEntity getPrice() {
                return price;
            }

            public String getStatus() {
                return status;
            }

            public ImgEntity getImg() {
                return img;
            }

            public String getFreight() {
                return freight;
            }

            public String getTime() {
                return time;
            }

            public TagEntity getTag() {
                return tag;
            }

            public String getDesc() {
                return desc;
            }

            public String getFav_num() {
                return fav_num;
            }

            public String getSub_classify_id() {
                return sub_classify_id;
            }

            public static class PriceEntity {
                private double current;
                private String prime;

                public void setCurrent(double current) {
                    this.current = current;
                }

                public void setPrime(String prime) {
                    this.prime = prime;
                }

                public double getCurrent() {
                    return current;
                }

                public String getPrime() {
                    return prime;
                }
            }

            public static class ImgEntity {
                private String img_url;
                private String img_w;
                private String img_h;

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public void setImg_w(String img_w) {
                    this.img_w = img_w;
                }

                public void setImg_h(String img_h) {
                    this.img_h = img_h;
                }

                public String getImg_url() {
                    return img_url;
                }

                public String getImg_w() {
                    return img_w;
                }

                public String getImg_h() {
                    return img_h;
                }
            }

            public static class TagEntity {
                private String title;
                private String color;

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getTitle() {
                    return title;
                }

                public String getColor() {
                    return color;
                }
            }
        }
    }
}
