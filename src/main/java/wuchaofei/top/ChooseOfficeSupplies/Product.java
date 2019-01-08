package wuchaofei.top.ChooseOfficeSupplies;

/**
 *
 * 商品
 * Created by cofco on 2019/1/7.
 */

public class Product {
//    名称
    private String name;
//    单位
    private String unit;

//    序列号
    private String sn;
//    描述
    private String desc;
//    价格
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", sn='" + sn + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}
