package wuchaofei.top.bean;

import wuchaofei.top.queue.LinkQueue;
import wuchaofei.top.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图的邻接矩阵存储结构
 * Created by cofco on 2018/12/29.
 */

public class VertexMatrix {
    /**
     * 定义最大顶点数
     */
    public static final int Max=33;
//    public static final int Max=6;
    public static final int MaxValue = Integer.MAX_VALUE;
    /**
     * 图的所有顶点
     */
   private int[][] vertices = new int[Max][Max];
   /*private int[][] vertices = {{
       MaxValue, 16,20,19,MaxValue,MaxValue
    },{
       16,MaxValue,11,MaxValue,6,5
    },{
       20,11,MaxValue,22,14,MaxValue
    },{
       19,MaxValue,22,MaxValue,18,MaxValue
    },{
       MaxValue,6,14,18,MaxValue,9
    },{
       MaxValue,5,MaxValue,MaxValue,9,MaxValue
    }};*/

   /**
    * 图的深度优先遍历算法中用到的顶点是否被访问过的标记数组
     * 0:未访问过该顶点
     * 2:已访问过该顶点
     */
   private int[] visited = new int[Max];

    /**
     * 图的广度优先搜索算法包含一个队列
     */
   private LinkQueue linkQueue;

    /**
     * 持有一个对象的实例
     */
   private static VertexMatrix vertexLink = new VertexMatrix();

    public int[][] getVertices() {
        return vertices;
    }

    public void setVertices(int[][] vertices) {
        this.vertices = vertices;
    }

    /**
     * 获取一个图的实例
     * @return
     */
    public static VertexMatrix getInstance(){
        return vertexLink;
    }

    public VertexMatrix getVertexLink() {
        return vertexLink;
    }

    public void setVertexLink(VertexMatrix vertexLink) {
        this.vertexLink = vertexLink;
    }

    /**
     * 初始化图的邻接矩阵表示
     */
    public void init(){
        // 创建33个顶点，并赋初值
        for (int i = 0; i < Max; i++) {
            for (int j = 0; j < Max; j++) {
                this.vertices[i][j] = MaxValue;
            }
        }
        // 读取边信息
        List<ArrayList<Integer>> result = FileUtil.readTextFile("src/main/resources/balls.txt");

//        对数据进行图的邻接表存储
        for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> line = result.get(i);
            for (int j = 0; j < line.size()-1; j++) {
                int row = line.get(j)-1;
                int colume = line.get(j+1)-1;
                if(vertices[row][colume]==MaxValue){
                    vertices[row][colume] = 1;
                }else{
                    vertices[row][colume] +=1;
                }

                if(vertices[colume][row]==MaxValue){
                    vertices[colume][row] = 1;
                }else{
                    vertices[colume][row] +=1;
                }
                /*for (int k = j+1; k < line.size(); k++) {
                    int row = line.get(j)-1;
                    int colume = line.get(k)-1;
                    if(vertices[row][colume]==MaxValue){
                        vertices[row][colume] = 1;
                    }else{
                        vertices[row][colume] +=1;
                    }

                    if(vertices[colume][row]==MaxValue){
                        vertices[colume][row] = 1;
                    }else{
                        vertices[colume][row] +=1;
                    }
                }*/
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        sb.append("VertexMatrix=[\n");
        for (int i = 0; i < Max; i++) {
            sb.append("[");
            for (int j = 0; j < Max; j++) {
                int value = vertices[i][j];
                if(value==MaxValue){
                    sb.append("∞,");
                }else{
                    sb.append(value + ",");
                }
            }
            sb.append("],\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    /**
     * 访问当前顶点
     * @param vertex
     */
    private void visit(int vertex){
        System.out.print((vertex+1)+" ");
        // 标记这个顶点已经访问过了
        visited[vertex] = 1;
    }
    /**
     * 对一个顶点的图的深度优先递归算法
     * @param v 顶点vertex
     */
    void deepFindFirst(int v){
        // 访问这个顶点，同时标记已经访问过了
        visit(v);
        // 查找vertex的第一个邻接点
        int w = firstVertex(v);
        while(w!=-1){
            if(visited[w]==0){
                // 对未访问过的第一个邻接点进行深度优先搜索
                deepFindFirst(w);
            }
            // 求v的下一个邻接点，若无邻接点返回-1
            w = nextVertex(v);
        }
    }

    /**
     * 对当前图进行深度优先搜素递归算法
     */
    public void travelDeepFindFirst(){
        for (int i = 0; i < Max; i++) {
            visited[i]=0;
        }

        for (int i = 0; i < Max; i++) {
            if(visited[i]==0){
                deepFindFirst(i);
            }
        }
        System.out.println();
    }

    /**
     * 查找顶点v的下一个邻接点，若无邻接点返回-1
     * 遍历顶点的边，返回未被访问过的第一条边
     * @param v
     * @return
     */
    private int nextVertex(int v) {
        return -1;
    }

    /**
     * 查找顶点vertex的第一个邻接点，失败返回-1
     * @param vertex 顶点下标
     * @return
     */
    private int firstVertex(int vertex) {
        return -1;
    }

    /**
     * 图的广度优先搜索
     * @param v 顶点索引
     */
    void BFS(int v){
//        访问顶点v,同时设置访问标记
        visit(v);
        // 加入队列
        linkQueue.addQueue(v);
        while(!linkQueue.isEmpty()){
            v = linkQueue.deleteQueue();
            /**
             * 查找第一个邻接点，无邻接点返回-1
             */
            int w = firstVertex(v);
            while(w!=-1){
                if(visited[w]==0){
                    visit(w);
                    linkQueue.addQueue(w);
                    visited[w]=1;
                }
                /**
                 * 求v的下一个邻接点，无邻接点返回-1
                 */
                w=nextVertex(v);
            }
        }
    }

    /**
     * 图的广度优先遍历算法
     */
    public void travelBFS(){
        for (int i = 0; i < Max; i++) {
            visited[i]=0;
        }

        this.linkQueue = new LinkQueue();

        for (int i = 0; i < Max; i++) {
            if(visited[i]==0){
                BFS(i);
            }
        }
        System.out.println();
    }

    /**
     * 求图的连通分量
     */
    public void component(){
        for (int i = 0; i < Max; i++) {
            visited[i]=0;
        }

        for (int i = 0; i < Max; i++) {
            if(visited[i]==0){
                System.out.println(i);
                deepFindFirst(i);
            }
        }
        System.out.println();
    }

    /**
     * 最小生成树 普里姆算法
     */
    public void minspantPrim(){
        // 用于存放V-U中各顶点到U中各顶点的权值
        int lowcost[] = new int[Max];
        int mincost,k = 0;

        // 用于记录该边在U中的那个顶点
        int teend[]=new int[Max];
        lowcost[0]=0;
        teend[0]=0;


        /**
         * 对辅助数组进行初始化
         */
        for (int i = 1; i < Max; i++) {
            teend[i]=0;
            lowcost[i] = vertices[0][i];
        }

        for (int i = 1; i < Max; i++) {
            mincost=MaxValue;
            int j=1;
            while(j<Max){
                if(lowcost[j]>0 && mincost>lowcost[j]){
                    mincost=lowcost[j];
                    k = j;
                }
                j++;
            }
//            选择一条一端在u，另一端在v-u上的所有边中权最小的边（k,teend[k]）
//            输出最小生成树的一条边
            System.out.print("("+(teend[k]+1)+","+(k+1)+"),");
            lowcost[k]=0; // 定点k加入到U中
            for (j = 0; j < Max; j++) {
                if(vertices[k][j] < lowcost[j]){
                    lowcost[j] = vertices[k][j];
                    teend[j] = k;
                }
            }
        }
    }
}
