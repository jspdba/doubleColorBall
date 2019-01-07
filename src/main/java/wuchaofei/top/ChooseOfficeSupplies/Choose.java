package wuchaofei.top.ChooseOfficeSupplies;

import wuchaofei.top.bean.VertexMatrix;
import wuchaofei.top.utils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 选择办公用品算法
 * 选择最优组合，发挥最大价值(21)
 *
 * Created by cofco on 2019/1/7.
 */

public class Choose {
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
        resolveData();
//        algorithm("1100015269");
        algorithm("1100011281");
    }

    /**
     * 解析excel数据
     * @return
     */
    private void resolveData(){
        List<Map<String, String>> data = ExcelUtil.readData("C:\\Users\\cofco\\Documents\\Tencent Files\\425258654\\FileRecv\\2018史泰博最新办公用品清单 (3).xlsx");
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
            product.setPrice(Double.parseDouble(p.get("5")));
            totalProducts.add(product);

            // 如果价格<=21 则可选
            if(product.getPrice()<= totalValue){
                canChooseProduct.add(product);
            }
        }
        data = null;
    }

    /**
     * 最优组合算法
     * @param sn 必须包含的商品的序列号
     */
    private void algorithm(String sn){
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
                surplusValue = totalValue - mustChooseProduct.getPrice();
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
        System.out.println("(剩余价值="+surplusValue+"，使用价值="+totalUsedValue+")");
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
