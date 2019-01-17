package wuchaofei.top.DynamicProgramming;

import wuchaofei.top.ChooseOfficeSupplies.Product;
import wuchaofei.top.utils.ExcelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 算法中需要的数据
 * Created by wuchaofei on 2019/1/11.
 */
public class Datas {
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
    /**
     * 用于计算的限制总值
     */
    private int calcTotalValue;

    /**
     * 按价格折叠的商品（相同价格的商品放到一个桶里）
     */
    private Map<Integer, ArrayList<Product>> foldedProductListMap;

    /**
     * 解析excel数据
     * @return
     */
    public void resolveData(String path){
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
        // 按价格折叠商品
        foldUpProducts();
    }

    /**
     * 按价格对可选商品列表折叠（相同价格只计算一次）
     */
    private void foldUpProducts(){
//        对可选择商品进行折叠
        foldedProductListMap = new HashMap<Integer, ArrayList<Product>>();
        for (Product product : canChooseProduct) {
            ArrayList<Product> productList = foldedProductListMap.get(product.getCalcPrice());
            if(productList == null){
                productList = new ArrayList<Product>();
            }
            productList.add(product);
            foldedProductListMap.put(product.getCalcPrice(), productList);
        }
        int size = foldedProductListMap.size();
        canChooseProduct = new ArrayList<Product>(size);
        // 将折叠后的数据填充进可选商品列表
        for (Map.Entry<Integer, ArrayList<Product>> productMap : foldedProductListMap.entrySet()) {
            canChooseProduct.add(productMap.getValue().get(0));
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

    public int getCalcTotalValue() {
        return (int)(totalValue*100);
    }

    public Map<Integer, ArrayList<Product>> getFoldedProductListMap() {
        return foldedProductListMap;
    }

    public void setFoldedProductListMap(Map<Integer, ArrayList<Product>> foldedProductListMap) {
        this.foldedProductListMap = foldedProductListMap;
    }
}
