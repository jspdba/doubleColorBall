package wuchaofei.top.lcs;

/**
 * lcs 最长公共子序列求法
 * Created by cofco on 2019/1/18.
 */

public class LonggestCommonSubsequence {
    // 用来保存计算过程中的最大公共子序列的长度
    private static int[][] c;
    // 指向计算c[i][j]时所选择的子问题的最优解
    private static String[][] b;

    // 字符串1转成的数组
    private static char[] x;
    // 字符串2转成的数组
    private static char[] y;


    /**
     * 计算公共子序列过程
     * @param X 字符串1
     * @param Y 字符串2
     */
    public static int commonSubsequence(final String X, final String Y){
        int m = X.length();
        int n = Y.length();

        c = new int[m + 1][n + 1];
        b = new String[m + 1][n + 1];

        x = X.toCharArray();
        y = Y.toCharArray();

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
        printLcs(m, n);
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
}
