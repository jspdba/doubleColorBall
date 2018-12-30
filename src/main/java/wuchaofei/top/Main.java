package wuchaofei.top;

import wuchaofei.top.bean.VertexMatrix;

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



//        图的邻接矩阵算法
        VertexMatrix vertexMatrix=new VertexMatrix();
        vertexMatrix.init();
        System.out.println(vertexMatrix.toString());
    }

}
