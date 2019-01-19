package wuchaofei.top.lcs;

import wuchaofei.top.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * lcs 最长公共子对象求法
 * Created by cofco on 2019/1/18.
 */

public class LongestCommonSubObject {
    // 用来保存计算过程中的最大公共子序列的长度
    private static int[][] c;
    // 指向计算c[i][j]时所选择的子问题的最优解
    private static String[][] b;

    // 字符串1转成的数组
    private static int[] x;
    // 字符串2转成的数组
    private static int[] y;


    /**
     * 计算公共子序列过程
     * @param X 字符串1
     * @param Y 字符串2
     */
    public static int commonSubsequence(final int[] X, final int[] Y){
        int m = X.length;
        int n = Y.length;

        c = new int[m + 1][n + 1];
        b = new String[m + 1][n + 1];

        x = X;
        y = Y;

        // 初始化
        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            c[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(x[i-1]==y[j-1]){
                    c[i][j] = c[i-1][j-1]+1;
                    b[i][j]="↖";
                }else if(c[i-1][j]>=c[i][j-1]){
                    c[i][j] = c[i-1][j];
                    b[i][j] = "↑";
                }else{
                    c[i][j] = c[i][j-1];
                    b[i][j] = "←";
                }
            }
        }
        // 打印c
//        for (int i = 0; i <= m; i++) {
//            for (int j = 0; j <= n; j++) {
//                System.out.print("("+c[i][j]+"," + (b[i][j] == null?"":b[i][j])+")" + "\t");
//            }
//            System.out.println();
//        }
//        printLcs(m, n);
        return c[m][n];
    }

    /**
     * 打印公共子序列
     */
    public static void printLcs(int i, int j){
        if(i==0 || j==0){
            return;
        }
        if("↖".equals(b[i][j])){
            printLcs(i-1,j-1);
            System.out.print(x[i-1]+" ");
        }else if("↑".equals(b[i][j])){
            printLcs(i-1,j);
        }else{
            printLcs( i,j-1);
        }
    }

    /**
     * 计算两个数组的相似性
     */
    public static void conputeSimilerArray(){
        List<ArrayList<Integer>> result = FileUtil.readTextFile("D:\\zhongliang\\doubleColorBall\\src\\main\\resources\\balls.txt");
        int[][] xy = parseToArray(result);
        // 统计
        int[][] similarArray = new int[result.size()][result.get(0).size()+1];


        // 计算相邻两期相似度
        for (int i = 0; i < xy.length; i++) {
            for (int j = 0; j < xy.length; j++) {
                if(i!=j){
                    int value = commonSubsequence(xy[i], xy[j]);
                    similarArray[i][value] += 1;
                }
            }
        }

        // 打印相似度相同的矩阵出现的次数
        for (int i = 0; i < similarArray.length; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(similarArray[i][j]+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 计算两个数组的相似性
     */
    public static void conputeSimilerArrayForTest(){
        List<ArrayList<Integer>> result = FileUtil.readTextFile("D:\\zhongliang\\doubleColorBall\\src\\main\\resources\\balls.txt");
        int[][] xy = parseToArray(result);
        // 统计
        int[][] similarArray = new int[result.size()][result.get(0).size()+1];

        int[][] testXy = new int[][]{
                {1,5,10,20,25,30},
                {7,20,26,30,31,33},
                {1,8,10,11,12,33},
                {4,5,6,7,8,9},
                {5,6,7,8,9,10},
                {6,7,8,9,10,11},
                {7,8,9,10,11,12},
        };

        // 计算相邻两期相似度
        for (int i = 0; i < testXy.length; i++) {
            for (int j = 0; j < xy.length; j++) {
                int value = commonSubsequence(xy[i], xy[j]);
                similarArray[i][value] += 1;
            }
        }

        // 打印相似度相同的矩阵出现的次数
        for (int i = 0; i < similarArray.length; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(similarArray[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static int[][] parseToArray(List<ArrayList<Integer>> src){
        int[][] arr = new int[src.size()][src.get(0).size()];
        for (int i = 0; i < src.size(); i++) {
            for (int j = 0; j < src.get(0).size(); j++) {
                arr[i][j] = src.get(i).get(j);
            }
        }
        return arr;
    }
}
