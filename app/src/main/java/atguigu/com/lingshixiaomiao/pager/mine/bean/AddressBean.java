package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.util.List;

/**
 * Created by lanmang on 2016/4/13.
 * 地址类
 */
public class AddressBean {

    @Override
    public String toString() {
        return "AddressBean{" +
                "rs_code='" + rs_code + '\'' +
                ", data=" + data +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    /**
     * rs_code : 1000
     * data : {"count":4,"items":[{"id":24838,"name":"宋梓良","phone":"18686823149","province":"北京市","city":"北京市","proper":"昌平区","full_add":"北七家镇 平西府 多彩家园","type":1},{"id":24850,"name":"123","phone":"12312312312","province":"北京市","city":"北京市","proper":"东城区","full_add":"123123123","type":0},{"id":24851,"name":"123","phone":"12312312312","province":"北京市","city":"北京市","proper":"东城区","full_add":"123123123","type":0},{"id":24852,"name":"123","phone":"12312312312","province":"北京市","city":"北京市","proper":"东城区","full_add":"123123123","type":0}]}
     * rs_msg : success
     */

    private String rs_code;
    /**
     * count : 4
     * items : [{"id":24838,"name":"宋梓良","phone":"18686823149","province":"北京市","city":"北京市","proper":"昌平区","full_add":"北七家镇 平西府 多彩家园","type":1},{"id":24850,"name":"123","phone":"12312312312","province":"北京市","city":"北京市","proper":"东城区","full_add":"123123123","type":0},{"id":24851,"name":"123","phone":"12312312312","province":"北京市","city":"北京市","proper":"东城区","full_add":"123123123","type":0},{"id":24852,"name":"123","phone":"12312312312","province":"北京市","city":"北京市","proper":"东城区","full_add":"123123123","type":0}]
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "count=" + count +
                    ", items=" + items +
                    '}';
        }

        private int count;
        /**
         * id : 24838
         * name : 宋梓良
         * phone : 18686823149
         * province : 北京市
         * city : 北京市
         * proper : 昌平区
         * full_add : 北七家镇 平西府 多彩家园
         * type : 1
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

            @Override
            public String toString() {
                return "ItemsEntity{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", phone='" + phone + '\'' +
                        ", province='" + province + '\'' +
                        ", city='" + city + '\'' +
                        ", proper='" + proper + '\'' +
                        ", full_add='" + full_add + '\'' +
                        ", type=" + type +
                        '}';
            }

            private int id;
            private String name;
            private String phone;
            private String province;
            private String city;
            private String proper;
            private String full_add;
            private int type;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getProper() {
                return proper;
            }

            public void setProper(String proper) {
                this.proper = proper;
            }

            public String getFull_add() {
                return full_add;
            }

            public void setFull_add(String full_add) {
                this.full_add = full_add;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
