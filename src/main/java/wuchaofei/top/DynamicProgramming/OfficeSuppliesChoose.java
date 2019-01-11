package wuchaofei.top.DynamicProgramming;

import wuchaofei.top.stack.SingleStack;

/**
 * 办公用品购买算法
 * 说明：仅能购买总价值为21元的商品
 * Created by cofco on 2019/1/10.
 */
public class OfficeSuppliesChoose {
    // 算法中需要的数据
    private Datas datas;

    // 累计所有商品的总金额
    private int minValue = Integer.MIN_VALUE;

    // 简单的顺序栈
    private SingleStack stack;
    // 保存结果的数据结构
//    private SingleStack resultStack;

    private int level = 0;

    /**
     * todo 此方法未完成记录选择过程（待完善）
     * 回溯算法求办公用品
     *
     * 1.要求每种物品只能选一件
     * 根据题目要求，这是一个01背包问题，可以用回溯法，也可以用动态规划算法
     * 1_1使用 回溯算法解决
     * 思路：可以看成1个问题的n个阶段，每个阶段选择一种商品。每个阶段都有选或不选两种方式
     */
    public void program_1_1(){
        // 初始化数据
        init();
        // 规定商品的最大个数
        int size = 3;
        stack = new SingleStack(size);
        // 输出总值
        System.out.println(recursion(0, 0, size));
    }

    /**
     * 动态规划求办公用品
     * todo 下一步是做一个折叠结构，将相同价格商品折叠起来看做同一个商品
     */
    public void program_1_2(){
        // 初始化数据
        init();
        // 规定商品的最大个数
        int size = this.datas.getCanChooseProduct().size();
        boolean[][] states = new boolean[size][this.datas.getCalcTotalValue()+1];
        states[0][0] = true;  // 第一行的数据要特殊处理
        states[0][this.datas.getCanChooseProduct().get(0).getCalcPrice()] = true;

        for (int i = 1; i < size; ++i) { // 动态规划
            for (int j = 0; j <= this.datas.getCalcTotalValue(); ++j) {// 不购买第 i 个商品
                if (states[i-1][j] == true) {
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= this.datas.getCalcTotalValue()-this.datas.getCanChooseProduct().get(i).getCalcPrice(); ++j) {// 购买第 i 个商品
                if (states[i-1][j]==true) {
                    states[i][j + this.datas.getCanChooseProduct().get(i).getCalcPrice()] = true;
                }
            }
        }

        int j;
        for (j = this.datas.getCalcTotalValue(); j >=0; j--) {
            if (states[size-1][j] == true) break; // 输出结果大于等于 w 的最小值
        }

        this.minValue = 0;
        for (int i = size-1; i >= 1; --i) { // i 表示二维数组中的行，j 表示列
            if(j-this.datas.getCanChooseProduct().get(i).getCalcPrice() >= 0 && states[i-1][j-this.datas.getCanChooseProduct().get(i).getCalcPrice()] == true) {
                System.out.println(this.datas.getCanChooseProduct().get(i)); // 购买这个商品
                this.minValue += this.datas.getCanChooseProduct().get(i).getCalcPrice();
                j = j - this.datas.getCanChooseProduct().get(i).getCalcPrice();
            } // else 没有购买这个商品，j 不变。
        }
        System.out.println((double)this.minValue/100);
    }

    /**
     * 打印当前栈中的值
     */
    private void displayStatck(){
        for (int i = 0; i < stack.size(); i++) {
            System.out.println(datas.getCanChooseProduct().get(i));
        }
        System.out.println("---------------------");
    }

    int recursion(int index, int totalValue, int maxSize){
        // 达到最大值
        if(index == maxSize || totalValue == datas.getCalcTotalValue()){
            System.out.println();
            if(minValue < totalValue){
                minValue = totalValue;
            }
            return minValue;
        }
//        不选择第i个商品
        recursion(index + 1, totalValue, maxSize);
//        选择第i个商品
        if(totalValue + datas.getCanChooseProduct().get(index).getCalcPrice() <= datas.getCalcTotalValue()){
//            stack.push(index);
            int result = recursion(index + 1, totalValue + datas.getCanChooseProduct().get(index).getCalcPrice(), maxSize);
//            stack.pop();
        }
        return minValue;
    }
    /**
     * 初始化算法中所需数据
     */
    void init(){
        this.datas = new Datas();
        this.datas.resolveData("C:\\Users\\cofco\\Documents\\Tencent Files\\425258654\\FileRecv\\2018史泰博最新办公用品清单 (3).xlsx");
    }

    public Datas getDatas() {
        return datas;
    }

    public void setDatas(Datas datas) {
        this.datas = datas;
    }
}
