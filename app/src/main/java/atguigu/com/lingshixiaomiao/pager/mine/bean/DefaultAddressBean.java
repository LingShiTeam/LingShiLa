package atguigu.com.lingshixiaomiao.pager.mine.bean;

/**
 * Created by lanmang on 2016/4/25.
 */
public class DefaultAddressBean {
    @Override
    public String toString() {
        return "设置默认地址 = {" +
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
