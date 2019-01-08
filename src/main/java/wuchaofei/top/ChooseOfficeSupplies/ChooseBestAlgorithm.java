package wuchaofei.top.ChooseOfficeSupplies;

import org.apache.commons.lang3.StringUtils;
import wuchaofei.top.utils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 最优选择算法
 * 主要目的：求怎样选择，才能利用最大价值
 * Created by cofco on 2019/1/8.
 */
public class ChooseBestAlgorithm {
    /**
     * 所有商品
     */
    private List<Product> totalProducts;
    /**
     * 可选择商品
     */
    private List<Product> canChooseProduct;

    /**
     * 限制商品总值
     */
    private final double totalValue = 21;

    public void run(){
        resolveData("C:\\Users\\cofco\\Documents\\Tencent Files\\425258654\\FileRecv\\2018史泰博最新办公用品清单 (3).xlsx");
        algorithm("1100020857");
    }

    /**
     * 根据序列号判断必选商品
     * @param index 商品序号
     */
    private void algorithm(int index) {
        int size = this.canChooseProduct.size();
        int[] uProduct = new int[size];
        int[] vProduct = new int[size];

        // 剩余价值
        double surplusValue = 21;
        // 使用价值
        double totalUsedValue = 0;
        // 剩余可选商品数
        int surplusCount = 0;

        // 必须选择的商品
        Product mustChooseProduct = null;
        int mustChooseProductIndex = index;
        // 初始化 u 代表已选择的商品
        mustChooseProduct = canChooseProduct.get(index);
        // 代表选择了这个商品
        uProduct[index] = 1;

        // 剩余价值
        surplusValue = surplusValue - mustChooseProduct.getPrice();
        totalUsedValue += mustChooseProduct.getPrice();

        // 初始化 v代表可选商品的个数
        for (int i = 0; i < size; i++) {
            Product product = canChooseProduct.get(i);
            int count = (int)(surplusValue/product.getPrice());
            // 可选商品数>0,否则不可选
            vProduct[i] = count;
            if(count>0){
                // 剩余可选商品数
                surplusCount += count;
            }
        }

        while(surplusCount > 0){
            // 选择一个最优商品
            // 首先选择一个价值最大商品
            int maxValue = -1;
            for (int i = 0; i < size; i++) {
                if(vProduct[i]>0){
                    if(maxValue==-1){
                        maxValue = i;
                    }else if(canChooseProduct.get(i).getPrice() > canChooseProduct.get(maxValue).getPrice()){
                        maxValue = i;
                    }
                }
            }
            if(maxValue>-1){
                uProduct[maxValue]++;
                // 更新剩余价值
                surplusValue = surplusValue - canChooseProduct.get(maxValue).getPrice();
                totalUsedValue += canChooseProduct.get(maxValue).getPrice();
                surplusCount = 0;
                for (int i = 0; i < size; i++) {
                    Product product = canChooseProduct.get(i);
                    // 如果是单独购买此商品可以购买数量
                    int count = (int)(surplusValue/product.getPrice());
                    // 可选商品数>0,否则不可选
                    vProduct[i] = count;
                    if(count>0){
                        // 剩余可选商品数
                        surplusCount += count;
                    }
                }
            }else{
                break;
            }
        }
        // 剩余价值
        System.out.println("(剩余价值="+ ExcelUtil.double2String(surplusValue)+"，使用价值="+totalUsedValue+")");
        // 选择的商品
        for (int i = 0; i < size; i++) {
            if(uProduct[i]>0){
                System.out.println(canChooseProduct.get(i)+"*" + uProduct[i] + canChooseProduct.get(i).getUnit());
            }
        }
    }

    /**
     * 解析excel数据
     * @return
     */
    private void resolveData(String path){
        List<Map<String, String>> data = ExcelUtil.readData(path);
        if(totalProducts==null){
            totalProducts = new ArrayList<Product>();
            canChooseProduct = new ArrayList<Product>();
        }
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> p = data.get(i);
            Product product = new Product();
            product.setName(p.get("1"));
            product.setUnit(p.get("2"));
            product.setSn(p.get("3"));
            product.setDesc(p.get("4"));
            product.setPrice(ExcelUtil.formatDouble1(Double.parseDouble(p.get("5"))));
            totalProducts.add(product);

            // 如果价格<=21 则可选
            if(product.getPrice()<= totalValue){
                canChooseProduct.add(product);
            }
        }
        data = null;
    }


    /**
     * 只选一种商品就可达到目的
     */
    private boolean directChooseProduct(String sn){
        // 是否继续
        boolean exit = false;
        if(StringUtils.isNotBlank(sn)){
            for (Product product : canChooseProduct) {
                if(product.getSn().equals(sn)){
                    int price = (int)(product.getPrice()*100);
                    int totalIntValue = (int)(totalValue*100);

                    if(totalIntValue%price==0){
                        System.out.println(product+"*"  + (totalIntValue/price) + product.getUnit());
                        exit = true;
                    }
                }
            }
        }else{
            for (Product product : canChooseProduct) {
                int price = (int)(product.getPrice()*100);
                int totalIntValue = (int)(totalValue*100);
                if(totalIntValue%price==0){
                    System.out.println(product+"*"  + (totalIntValue/price) + product.getUnit());
                }
            }
        }
        return exit;
    }
    private void algorithm(String sn){
        if(directChooseProduct(sn)){
            return;
        }

        // todo 这里不继续写了，有时间再写
        int size = this.canChooseProduct.size();
        int[] uProduct = new int[size];
        int[] vProduct = new int[size];

        // 剩余价值
        double surplusValue = 21;
        // 使用价值
        double totalUsedValue = 0;
        // 剩余可选商品数
        int surplusCount = 0;

        // 必须选择的商品
        Product mustChooseProduct = null;
        int mustChooseProductIndex = -1;
        // 初始化 u 代表已选择的商品
        for (int i = 0; i < size; i++) {
            Product product = canChooseProduct.get(i);
            if(product.getSn().equals(sn)){
                // 代表选择了这个商品
                uProduct[i] = 1;
                mustChooseProduct = product;
                mustChooseProductIndex = i;
                // 剩余价值
                surplusValue = surplusValue - mustChooseProduct.getPrice();
                totalUsedValue += mustChooseProduct.getPrice();
                break;
            }
        }
        // 初始化 v代表可选商品的个数
        for (int i = 0; i < size; i++) {
            Product product = canChooseProduct.get(i);
            // 如果是单独购买此商品可以购买数量
            int count = (int)(surplusValue/product.getPrice());
            // 可选商品数>0,否则不可选
            vProduct[i] = count;
            if(count>0){
                // 剩余可选商品数
                surplusCount += count;
            }
        }

        while(surplusCount > 0){
            // 选择一个最优商品
            // 首先选择一个价值最大商品
            int maxValue = -1;
            for (int i = 0; i < size; i++) {
                if(vProduct[i]>0){
                    if(maxValue==-1){
                        maxValue = i;
                    }else if(canChooseProduct.get(i).getPrice() > canChooseProduct.get(maxValue).getPrice()){
                        maxValue = i;
                    }
                }
            }
            if(maxValue>-1){
                uProduct[maxValue]++;
                // 更新剩余价值
                surplusValue = surplusValue - canChooseProduct.get(maxValue).getPrice();
                totalUsedValue += canChooseProduct.get(maxValue).getPrice();
                surplusCount = 0;
                for (int i = 0; i < size; i++) {
                    Product product = canChooseProduct.get(i);
                    // 如果是单独购买此商品可以购买数量
                    int count = (int)(surplusValue/product.getPrice());
                    // 可选商品数>0,否则不可选
                    vProduct[i] = count;
                    if(count>0){
                        // 剩余可选商品数
                        surplusCount += count;
                    }
                }
            }else{
                break;
            }
        }
        // 剩余价值
        System.out.println("(剩余价值="+ExcelUtil.double2String(surplusValue) + "，使用价值="+totalUsedValue+")");
        // 选择的商品
        for (int i = 0; i < size; i++) {
            if(uProduct[i]>0){
                System.out.println(canChooseProduct.get(i)+"*" + uProduct[i] + canChooseProduct.get(i).getUnit());
            }
        }
    }

    public List<Product> getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(List<Product> totalProducts) {
        this.totalProducts = totalProducts;
    }

    public List<Product> getCanChooseProduct() {
        return canChooseProduct;
    }

    public void setCanChooseProduct(List<Product> canChooseProduct) {
        this.canChooseProduct = canChooseProduct;
    }

    public double getTotalValue() {
        return totalValue;
    }
}
