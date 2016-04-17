package atguigu.com.lingshixiaomiao.pager.home.bean;

import java.util.List;

/**
 * Created by Liu_haiwei on 2016/4/15.
 * 菜单详情页面的标题实体类
 */
public class MenuTabTitleBean {

    /**
     * rs_code : 1000
     * data : {"count":5,"items":[{"id":96,"title":"牛肉干"},{"id":97,"title":"猪肉脯"},{"id":98,"title":"其它"},{"id":99,"title":"海味/海苔"},{"id":101,"title":"豆干类"}]}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * count : 5
     * items : [{"id":96,"title":"牛肉干"},{"id":97,"title":"猪肉脯"},{"id":98,"title":"其它"},{"id":99,"title":"海味/海苔"},{"id":101,"title":"豆干类"}]
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
         * id : 96
         * title : 牛肉干
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
            private int id;
            private String title;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
