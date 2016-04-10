package atguigu.com.lingshixiaomiao.pager.scale.bean;

import java.util.List;

/**
 * 热卖中页面的数据Bean
 * Created by Administrator on 2016/4/9 0009.
 */
public class ScallingBean {

    /**
     * count : 18
     * items : [{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":8753,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e6b/6b/b/baf3b6045e521cc770528af6c218ee6b.jpeg","img_w":640},"price":{"current":11.2,"prime":25.8},"sold_num":0,"special_num":702,"special_percentage":69,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.3折"},"time":1460376000,"title":"[俄罗斯]麦客喜(Maxi chips)乳酪味薯片100g","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9416,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/18f/8f/f/dfccfc5446697728be17f9eb59e7818f.jpeg","img_w":640},"price":{"current":9.9,"prime":18.8},"sold_num":0,"special_num":623,"special_percentage":62,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.3折"},"time":1460376000,"title":"[台湾北田] 蛋黄口味米饼100g","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9157,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/a9d/9d/d/cda2a27ca1cf815bea93ef31c3eb3a9d.jpeg","img_w":640},"price":{"current":4.9,"prime":12.9},"sold_num":0,"special_num":607,"special_percentage":60,"status":0,"surplus_num":0,"tag":{"color":1,"title":"3.8折"},"time":1460376000,"title":"[韩国啵乐乐] 草莓味儿童果味饮料","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9159,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/618/18/8/579419bf2d77431e232d4b111bef9618.jpeg","img_w":640},"price":{"current":12.5,"prime":25},"sold_num":0,"special_num":619,"special_percentage":61,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.0折"},"time":1460376000,"title":"[台湾咔咔] 非油炸海苔味龙虾饼90g海苔味","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9209,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/3d2/d2/2/0da798762b76fd2b2270c17a51e1b3d2.jpeg","img_w":640},"price":{"current":25.5,"prime":48},"sold_num":0,"special_num":670,"special_percentage":67,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.3折"},"time":1460376000,"title":"[意大利爱利地] 榛子华夫块  进口零食松脆香醇","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":8221,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/79c/9c/c/77b26d6fccfc955849769adb2ebfa79c.jpeg","img_w":640},"price":{"current":19.89,"prime":48},"sold_num":0,"special_num":683,"special_percentage":67,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.1折"},"time":1460376000,"title":"[美国Fisher] 纷时乐美式蜂蜜烤扁桃仁140g","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9232,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d19/19/9/19b8c4b037b168b9e8850f90fa79bd19.jpeg","img_w":640},"price":{"current":13.9,"prime":19},"sold_num":0,"special_num":677,"special_percentage":67,"status":0,"surplus_num":0,"tag":{"color":1,"title":"7.3折"},"time":1460376000,"title":"[好丽友]木糖醇3+檬萌C粒粒出无糖口香糖","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9235,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/29c/9c/c/0cfb300cb38017bc89772bd9edb6629c.jpg","img_w":640},"price":{"current":13.9,"prime":30},"sold_num":0,"special_num":779,"special_percentage":74,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.6折"},"time":1460376000,"title":"[泰国小老板] 辛辣味香脆烤海苔卷  泰国爆款","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9124,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/de6/e6/6/775bc78a72170d0e338e0ed17ae64de6.jpeg","img_w":640},"price":{"current":5.9,"prime":9.9},"sold_num":0,"special_num":1007,"special_percentage":84,"status":0,"surplus_num":0,"tag":{"color":1,"title":"6.0折"},"time":1460376000,"title":"[台湾张君雅小妹妹]日式休闲丸子","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9221,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/238/38/8/de5416a38f86412da2e6e25533817238.jpeg","img_w":640},"price":{"current":6.9,"prime":15.9},"sold_num":0,"special_num":777,"special_percentage":77,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.3折"},"time":1460376000,"title":"[马来西亚TATAWA] 皇冠提拉米苏曲奇","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9132,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/f8e/8e/e/7bf4e860094e7603795b0e0ccc8fdf8e.jpeg","img_w":640},"price":{"current":8.19,"prime":18},"sold_num":0,"special_num":633,"special_percentage":61,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.6折"},"time":1460376000,"title":"[韩福10.2] 泡菜味即食海苔","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9211,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/266/66/6/3742d0f66c58c2dd0b2cc8a1a6ea0266.jpeg","img_w":640},"price":{"current":25.9,"prime":48},"sold_num":0,"special_num":776,"special_percentage":77,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.4折"},"time":1460376000,"title":"[意大利爱利地] 香草华夫块  进口零食","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9215,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/199/99/9/e17b01c75c0accc991421a34d626b199.jpeg","img_w":640},"price":{"current":25.9,"prime":59},"sold_num":0,"special_num":840,"special_percentage":84,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.4折"},"time":1460376000,"title":"[意大利爱利地] 经典早餐酥 酥性饼干","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9535,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/285/85/5/249c76f124303dfd4d14de47585d5285.jpeg","img_w":640},"price":{"current":12.5,"prime":26.5},"sold_num":0,"special_num":700,"special_percentage":70,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.7折"},"time":1460289600,"title":"[韩国有东]黄桃罐头 新鲜黄桃滑嫩好口感","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9333,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/4f4/f4/4/c942c441d37c55221de1c77672ff54f4.jpeg","img_w":640},"price":{"current":5,"prime":6.9},"sold_num":0,"special_num":752,"special_percentage":75,"status":0,"surplus_num":0,"tag":{"color":1,"title":"7.2折"},"time":1460347200,"title":"[老奶奶] 花生米142g/袋 花生米中的挚爱（此批次保质期到5月30日）","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":6856,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e57/57/7/6cf6441e220ff8f454a9aa9b48a4ae57.jpeg","img_w":640},"price":{"current":4.9,"prime":24.9},"sold_num":0,"special_num":845,"special_percentage":66,"status":0,"surplus_num":0,"tag":{"color":1,"title":"2.0折"},"time":1460604600,"title":"[九日] 韩国进口蜂蜜黄油薯片60g 土豪薯片（此批次保质期到4月30日）","type":1},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9248,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/6da/da/a/d0de0a032ad7d8ee2a8c39df691f46da.jpeg","img_w":640},"price":{"current":5,"prime":20.8},"sold_num":0,"special_num":666,"special_percentage":63,"status":1,"surplus_num":0,"tag":{"color":1,"title":"2.4折"},"time":1460347200,"title":"[韩国可拉奥] 蔓越莓西饼曲奇酥软饼干170g（此批次保质期到5月18日）","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":7866,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/7ec/ec/c/e8704f14d51773994e1e4b0113bf27ec.jpeg","img_w":640},"price":{"current":7.5,"prime":18.9},"sold_num":0,"special_num":880,"special_percentage":73,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.0折"},"time":1460347200,"title":"[比利时和情]原味焦糖饼干 咖啡伴侣（此批保质期到5月4日）","type":2}]
     */

    private DataEntity data;
    /**
     * data : {"count":18,"items":[{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":8753,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e6b/6b/b/baf3b6045e521cc770528af6c218ee6b.jpeg","img_w":640},"price":{"current":11.2,"prime":25.8},"sold_num":0,"special_num":702,"special_percentage":69,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.3折"},"time":1460376000,"title":"[俄罗斯]麦客喜(Maxi chips)乳酪味薯片100g","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9416,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/18f/8f/f/dfccfc5446697728be17f9eb59e7818f.jpeg","img_w":640},"price":{"current":9.9,"prime":18.8},"sold_num":0,"special_num":623,"special_percentage":62,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.3折"},"time":1460376000,"title":"[台湾北田] 蛋黄口味米饼100g","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9157,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/a9d/9d/d/cda2a27ca1cf815bea93ef31c3eb3a9d.jpeg","img_w":640},"price":{"current":4.9,"prime":12.9},"sold_num":0,"special_num":607,"special_percentage":60,"status":0,"surplus_num":0,"tag":{"color":1,"title":"3.8折"},"time":1460376000,"title":"[韩国啵乐乐] 草莓味儿童果味饮料","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9159,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/618/18/8/579419bf2d77431e232d4b111bef9618.jpeg","img_w":640},"price":{"current":12.5,"prime":25},"sold_num":0,"special_num":619,"special_percentage":61,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.0折"},"time":1460376000,"title":"[台湾咔咔] 非油炸海苔味龙虾饼90g海苔味","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9209,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/3d2/d2/2/0da798762b76fd2b2270c17a51e1b3d2.jpeg","img_w":640},"price":{"current":25.5,"prime":48},"sold_num":0,"special_num":670,"special_percentage":67,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.3折"},"time":1460376000,"title":"[意大利爱利地] 榛子华夫块  进口零食松脆香醇","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":8221,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/79c/9c/c/77b26d6fccfc955849769adb2ebfa79c.jpeg","img_w":640},"price":{"current":19.89,"prime":48},"sold_num":0,"special_num":683,"special_percentage":67,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.1折"},"time":1460376000,"title":"[美国Fisher] 纷时乐美式蜂蜜烤扁桃仁140g","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9232,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/d19/19/9/19b8c4b037b168b9e8850f90fa79bd19.jpeg","img_w":640},"price":{"current":13.9,"prime":19},"sold_num":0,"special_num":677,"special_percentage":67,"status":0,"surplus_num":0,"tag":{"color":1,"title":"7.3折"},"time":1460376000,"title":"[好丽友]木糖醇3+檬萌C粒粒出无糖口香糖","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9235,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/29c/9c/c/0cfb300cb38017bc89772bd9edb6629c.jpg","img_w":640},"price":{"current":13.9,"prime":30},"sold_num":0,"special_num":779,"special_percentage":74,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.6折"},"time":1460376000,"title":"[泰国小老板] 辛辣味香脆烤海苔卷  泰国爆款","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9124,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/de6/e6/6/775bc78a72170d0e338e0ed17ae64de6.jpeg","img_w":640},"price":{"current":5.9,"prime":9.9},"sold_num":0,"special_num":1007,"special_percentage":84,"status":0,"surplus_num":0,"tag":{"color":1,"title":"6.0折"},"time":1460376000,"title":"[台湾张君雅小妹妹]日式休闲丸子","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9221,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/238/38/8/de5416a38f86412da2e6e25533817238.jpeg","img_w":640},"price":{"current":6.9,"prime":15.9},"sold_num":0,"special_num":777,"special_percentage":77,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.3折"},"time":1460376000,"title":"[马来西亚TATAWA] 皇冠提拉米苏曲奇","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9132,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/f8e/8e/e/7bf4e860094e7603795b0e0ccc8fdf8e.jpeg","img_w":640},"price":{"current":8.19,"prime":18},"sold_num":0,"special_num":633,"special_percentage":61,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.6折"},"time":1460376000,"title":"[韩福10.2] 泡菜味即食海苔","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9211,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/266/66/6/3742d0f66c58c2dd0b2cc8a1a6ea0266.jpeg","img_w":640},"price":{"current":25.9,"prime":48},"sold_num":0,"special_num":776,"special_percentage":77,"status":0,"surplus_num":0,"tag":{"color":1,"title":"5.4折"},"time":1460376000,"title":"[意大利爱利地] 香草华夫块  进口零食","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9215,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/199/99/9/e17b01c75c0accc991421a34d626b199.jpeg","img_w":640},"price":{"current":25.9,"prime":59},"sold_num":0,"special_num":840,"special_percentage":84,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.4折"},"time":1460376000,"title":"[意大利爱利地] 经典早餐酥 酥性饼干","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9535,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/285/85/5/249c76f124303dfd4d14de47585d5285.jpeg","img_w":640},"price":{"current":12.5,"prime":26.5},"sold_num":0,"special_num":700,"special_percentage":70,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.7折"},"time":1460289600,"title":"[韩国有东]黄桃罐头 新鲜黄桃滑嫩好口感","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9333,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/4f4/f4/4/c942c441d37c55221de1c77672ff54f4.jpeg","img_w":640},"price":{"current":5,"prime":6.9},"sold_num":0,"special_num":752,"special_percentage":75,"status":0,"surplus_num":0,"tag":{"color":1,"title":"7.2折"},"time":1460347200,"title":"[老奶奶] 花生米142g/袋 花生米中的挚爱（此批次保质期到5月30日）","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":6856,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e57/57/7/6cf6441e220ff8f454a9aa9b48a4ae57.jpeg","img_w":640},"price":{"current":4.9,"prime":24.9},"sold_num":0,"special_num":845,"special_percentage":66,"status":0,"surplus_num":0,"tag":{"color":1,"title":"2.0折"},"time":1460604600,"title":"[九日] 韩国进口蜂蜜黄油薯片60g 土豪薯片（此批次保质期到4月30日）","type":1},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":9248,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/6da/da/a/d0de0a032ad7d8ee2a8c39df691f46da.jpeg","img_w":640},"price":{"current":5,"prime":20.8},"sold_num":0,"special_num":666,"special_percentage":63,"status":1,"surplus_num":0,"tag":{"color":1,"title":"2.4折"},"time":1460347200,"title":"[韩国可拉奥] 蔓越莓西饼曲奇酥软饼干170g（此批次保质期到5月18日）","type":2},{"begin_hour":"","desc":"","fav_num":0,"freight":0,"guide_type":0,"id":7866,"img":{"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/7ec/ec/c/e8704f14d51773994e1e4b0113bf27ec.jpeg","img_w":640},"price":{"current":7.5,"prime":18.9},"sold_num":0,"special_num":880,"special_percentage":73,"status":0,"surplus_num":0,"tag":{"color":1,"title":"4.0折"},"time":1460347200,"title":"[比利时和情]原味焦糖饼干 咖啡伴侣（此批保质期到5月4日）","type":2}]}
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
        private int count;
        /**
         * begin_hour :
         * desc :
         * fav_num : 0
         * freight : 0
         * guide_type : 0
         * id : 8753
         * img : {"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/e6b/6b/b/baf3b6045e521cc770528af6c218ee6b.jpeg","img_w":640}
         * price : {"current":11.2,"prime":25.8}
         * sold_num : 0
         * special_num : 702
         * special_percentage : 69
         * status : 0
         * surplus_num : 0
         * tag : {"color":1,"title":"4.3折"}
         * time : 1460376000
         * title : [俄罗斯]麦客喜(Maxi chips)乳酪味薯片100g
         * type : 2
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
            private String begin_hour;
            private String desc;
            private int fav_num;
            private int freight;
            private int guide_type;
            private int id;
            /**
             * img_h : 640
             * img_url : http://img.lingshi.cccwei.com/lingshi/e6b/6b/b/baf3b6045e521cc770528af6c218ee6b.jpeg
             * img_w : 640
             */

            private ImgEntity img;
            /**
             * current : 11.2
             * prime : 25.8
             */

            private PriceEntity price;
            private int sold_num;
            private int special_num;
            private int special_percentage;
            private int status;
            private int surplus_num;
            /**
             * color : 1
             * title : 4.3折
             */

            private TagEntity tag;
            private int time;
            private String title;
            private int type;

            public void setBegin_hour(String begin_hour) {
                this.begin_hour = begin_hour;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setFav_num(int fav_num) {
                this.fav_num = fav_num;
            }

            public void setFreight(int freight) {
                this.freight = freight;
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

            public void setPrice(PriceEntity price) {
                this.price = price;
            }

            public void setSold_num(int sold_num) {
                this.sold_num = sold_num;
            }

            public void setSpecial_num(int special_num) {
                this.special_num = special_num;
            }

            public void setSpecial_percentage(int special_percentage) {
                this.special_percentage = special_percentage;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setSurplus_num(int surplus_num) {
                this.surplus_num = surplus_num;
            }

            public void setTag(TagEntity tag) {
                this.tag = tag;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBegin_hour() {
                return begin_hour;
            }

            public String getDesc() {
                return desc;
            }

            public int getFav_num() {
                return fav_num;
            }

            public int getFreight() {
                return freight;
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

            public PriceEntity getPrice() {
                return price;
            }

            public int getSold_num() {
                return sold_num;
            }

            public int getSpecial_num() {
                return special_num;
            }

            public int getSpecial_percentage() {
                return special_percentage;
            }

            public int getStatus() {
                return status;
            }

            public int getSurplus_num() {
                return surplus_num;
            }

            public TagEntity getTag() {
                return tag;
            }

            public int getTime() {
                return time;
            }

            public String getTitle() {
                return title;
            }

            public int getType() {
                return type;
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

            public static class TagEntity {
                private int color;
                private String title;

                public void setColor(int color) {
                    this.color = color;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getColor() {
                    return color;
                }

                public String getTitle() {
                    return title;
                }
            }
        }
    }
}
