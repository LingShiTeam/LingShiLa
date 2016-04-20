package atguigu.com.lingshixiaomiao.pager.mine.bean;

import java.util.List;

/**
 * Created by lanmang on 2016/4/15.
 */
public class CityBean {

    @Override
    public String toString() {
        return "ProvinceEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    private String id;
    private String name;
    /**
     * county : [{"id":"01","name":"东城区"},{"id":"02","name":"西城区"},{"id":"05","name":"朝阳区"},{"id":"06","name":"丰台区"},{"id":"07","name":"石景山区"},{"id":"08","name":"海淀区"},{"id":"09","name":"门头沟区"},{"id":"11","name":"房山区"},{"id":"12","name":"通州区"},{"id":"13","name":"顺义区"},{"id":"14","name":"昌平区"},{"id":"15","name":"大兴区"},{"id":"16","name":"怀柔区"},{"id":"17","name":"平谷区"}]
     * id : 01
     * name : 市辖区
     */

    private List<CityEntity> city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityEntity> getCity() {
        return city;
    }

    public void setCity(List<CityEntity> city) {
        this.city = city;
    }

    public static class CityEntity {

        @Override
        public String toString() {
            return "CityEntity{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", county=" + county +
                    '}';
        }

        private String id;
        private String name;
        /**
         * id : 01
         * name : 东城区
         */

        private List<CountyEntity> county;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CountyEntity> getCounty() {
            return county;
        }

        public void setCounty(List<CountyEntity> county) {
            this.county = county;
        }

        public static class CountyEntity {

            @Override
            public String toString() {
                return "CountyEntity{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
