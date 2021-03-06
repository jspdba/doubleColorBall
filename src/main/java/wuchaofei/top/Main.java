package wuchaofei.top;

import wuchaofei.top.lcs.LongestCommonSubObject;
import wuchaofei.top.lcs.LonggestCommonSubsequence;

/**
 * Created by cofco on 2018/12/29.
 */

public class Main {
    public static void main(String[] args) {
//        VertexLink vertexLink = VertexLink.getInstance();
//        vertexLink.init();
//        System.out.println(vertexLink.toString());
//        图的深度优先遍历
//        vertexLink.travelDeepFindFirst();
//        图的广度优先遍历
//        vertexLink.travelBFS();
//        求图的连通分量
//        vertexLink.component();



/*//        图的邻接矩阵算法
        VertexMatrix vertexMatrix = new VertexMatrix();
        vertexMatrix.init();
//        System.out.println(vertexMatrix.toString());
//        求最短路径的普里姆算法
//        vertexMatrix.minspantPrim(6-1);
        // 迪杰斯特拉算法求最短路径
//        vertexMatrix.shortestPath(1-1);
//        vertexMatrix.runFloyed();
//        vertexMatrix.minspantPrim(1-1);

//        vertexMatrix.minspantPrimWithTopoSort(4);
        // 球最短路径求法（一组数之间路径加1）
        vertexMatrix.primImpromve();
//        vertexMatrix.toDot();*/

        // 最优组合
//        Choose choose = new Choose();
//        choose.run();

//        ChooseBestAlgorithm chooseBestAlgorithm = new ChooseBestAlgorithm();
//        chooseBestAlgorithm.run();

//        Queen8.cal8queens(0);
        // 0-1背包问题求解
//        ZeroBag.run();

        // 办公用品选择
//        OfficeSuppliesChoose officeSuppliesChoose = new OfficeSuppliesChoose();
//        officeSuppliesChoose.program_1_2();

        // 计算公共子序列
//        LonggestCommonSubsequence.commonSubsequence("123456", "23456586");
        // 计算一组数列其中两个的的相似度，
//        LongestCommonSubObject.conputeSimilerArray();
        LongestCommonSubObject.conputeSimilerArrayForTest();
    }

}
