package wuchaofei.top.bean;

import wuchaofei.top.queue.LinkQueue;
import wuchaofei.top.sort.SortUtil;
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
    public static final int Max = 33;
//        public static final int Max = 6;
//    public static final int Max = 7;
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
   /*private int[][] vertices = {{
       0,10,2,MaxValue,MaxValue,MaxValue,MaxValue
    },{
       10,0,MaxValue,MaxValue,1,MaxValue,MaxValue
    },{
       2,MaxValue,0,2,MaxValue,11,MaxValue
    },{
       MaxValue,MaxValue,2,0,4,6,MaxValue
    },{
       MaxValue,1,MaxValue,4,0,MaxValue,7
    },{
       MaxValue,MaxValue,11,6,MaxValue,0,3
    },{
        MaxValue,MaxValue,MaxValue,MaxValue,7,3,0
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
        /*if(true){
            return;
        }*/
        // 创建33个顶点，并赋初值
        for (int i = 0; i < Max; i++) {
            for (int j = 0; j < Max; j++) {
                this.vertices[i][j] = MaxValue;
            }
        }
        // 读取边信息
        List<ArrayList<Integer>> result = FileUtil.readTextFile("src/main/resources/balls.txt");

//        对数据进行图的邻接表存储
//        对相邻数据关联+1
        /*for (int i = 0; i < result.size(); i++) {
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
            }
        }*/

        /*for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> line = result.get(i);
            for (int j = 0; j < line.size()-1; j++) {
                int row = line.get(j)-1;
                int colume = line.get(j+1)-1;
                if(vertices[row][colume]==MaxValue){
                    vertices[row][colume] = -1;
                }else{
                    vertices[row][colume] -=1;
                }
                // 双向
                if(vertices[colume][row]==MaxValue){
                    vertices[colume][row] = -1;
                }else{
                    vertices[colume][row] -=1;
                }
            }
        }*/

        // 对同组的数据关联 +1
        /*for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> line = result.get(i);
            for (int j = 0; j < line.size()-1; j++) {
                for (int k = j+1; k < line.size(); k++) {
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
                }
            }
        }*/
//        对同组的数据关联 -1
        for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> line = result.get(i);
            for (int j = 0; j < line.size()-1; j++) {
                for (int k = j+1; k < line.size(); k++) {
                    int row = line.get(j)-1;
                    int colume = line.get(k)-1;
                    if(vertices[row][colume]==MaxValue){
                        vertices[row][colume] = -1;
//                        vertices[row][colume] = 1;
                    }else{
                        vertices[row][colume] -=1;
//                        vertices[row][colume] +=1;
                    }
                    if(vertices[colume][row]==MaxValue){
                        vertices[colume][row] = -1;
//                        vertices[colume][row] = 1;
                    }else{
                        vertices[colume][row] -=1;
//                        vertices[colume][row] +=1;
                    }
                }
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
     * 访问当前顶点
     * @param vertex
     */
    private void visitS(int[] s,int vertex){
//        System.out.print((vertex+1)+" ");
        // 标记这个顶点已经访问过了
        s[vertex] = 1;
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
                if(lowcost[j]!=0 && mincost>lowcost[j]){
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

    /**
     * 最小生成树 普里姆算法
     */
    public void minspantPrim(int startVertex){
        // 用于存放V-U中各顶点到U中各顶点的权值
        int lowcost[] = new int[Max];
        int mincost,k = startVertex;

        // 用于记录该边在U中的那个顶点
        int teend[] = new int[Max];

        // 设置起点
        lowcost[startVertex] = 0;
        teend[startVertex] = startVertex;

        /**
         * 对辅助数组进行初始化
         */
        for (int i = 0; i < Max; i++) {
            if(i!=startVertex){
                teend[i] = startVertex;
                lowcost[i] = vertices[startVertex][i];
            }
        }
        for (int i = 0; i < Max; i++) {
            mincost = MaxValue;
            if(i!=startVertex){
                for (int j = 0; j < Max; j++) {
                    if(lowcost[j]!=0 && mincost>lowcost[j]){
                        mincost = lowcost[j];
                        k = j;
                    }
                }
//            选择一条一端在u，另一端在v-u上的所有边中权最小的边（k,teend[k]）
//            输出最小生成树的一条边
                System.out.print("("+(teend[k]+1)+","+(k+1)+"),");
                lowcost[k]=0; // 顶点k加入到U中
                for (int j = 0; j < Max; j++) {
                    if(lowcost[j]!=0 && j!=startVertex && vertices[k][j] < lowcost[j]){
                        lowcost[j] = vertices[k][j];
                        teend[j] = k;
                    }
                }
            }
        }

    }
    /**
     * 最小生成树 普里姆算法
     */
    public void minspantPrim(int[] store, int startVertex, int n){
        // 用于存放V-U中各顶点到U中各顶点的权值
        int lowcost[] = new int[Max];
        int mincost,k = startVertex;
        // 总的权值
        int totalCost = 0;

        // 用于记录该边在U中的那个顶点
        int teend[] = new int[Max];

        // 设置起点
        lowcost[startVertex] = 0;
        teend[startVertex] = startVertex;

        /**
         * 对辅助数组进行初始化
         */
        for (int i = 0; i < Max; i++) {
            if(i!=startVertex){
                teend[i] = startVertex;
                lowcost[i] = vertices[startVertex][i];
            }
        }
        // 找到 n 个节点
        int findedCount = 0;
        // 用于保存顶点的数组

        for (int i = 0; i < Max; i++) {
            mincost = MaxValue;
            if(i!=startVertex){
                for (int j = 0; j < Max; j++) {
                    if(lowcost[j]!=0 && mincost>lowcost[j]){
                        mincost = lowcost[j];
                        k = j;
                    }
                }
//            选择一条一端在u，另一端在v-u上的所有边中权最小的边（k,teend[k]）
//            输出最小生成树的一条边
                System.out.print("("+(teend[k]+1)+","+(k+1)+")\t");
                SortUtil.insertSort(store, teend[k]+1, k+1, n+1);
                totalCost += mincost;
                lowcost[k]=0; // 顶点k加入到U中
                findedCount += 1;
                if(findedCount==n) {
                    break;
                }
                for (int j = 0; j < Max; j++) {
                    if(lowcost[j]!=0 && j!=startVertex && vertices[k][j] < lowcost[j]){
                        lowcost[j] = vertices[k][j];
                        teend[j] = k;
                    }
                }
            }
        }
        store[n+1] = totalCost;
        System.out.println();
    }


    /**
     * 求最短路径的迪杰斯特拉算法
     * v到各顶点的最短路径算法
     * @param v 顶点v
     */
    public void shortestPath(int v){
        int[] s = new int[Max];
        int[] dist = new int[Max];                // v 到各顶点之间最短路径的路径长度
        int[][] path = new int[Max][Max];       // 第i行元素记录了源点v到顶点i的最短路径上经过的所有顶点（序列）
        int[] pos = new int[Max];
        int u,w = 0;

        // 对辅助数组进行初始化
        for (int i = 0; i < Max; i++) {
            s[i] = 0;                           // 标记数组置0
            dist[i] = vertices[v][i];           // 将邻接矩阵第v行元素依次送dist数组
            path[i][0] = v;                     // 源点v到各顶点的路径置初值
            pos[i] = 0;                         // 第i条路径的位置计数器置初值
        }
        visitS(s, v);
        int count = 1; //计数器赋初值

        while(count < Max){
            u = minDist(s, dist);       // 利用s和dist在尚未找到最短路径的顶点中确定一个与v最近的顶点u
            visitS(s,u);
            pos[u] += 1;
            path[u][pos[u]] = u;      // 将u添加到从v到u的最短路径中
            count++;
            for (int i = 0; i < Max; i++) {
                if(s[i] == 1 || vertices[u][i] == MaxValue || dist[u] == MaxValue){
                    continue;
                }
                w = i;
                if(dist[u] + vertices[u][w] < dist[w]){
                    dist[w] = dist[u]+vertices[u][w];
                    for (int j = 0; j <= pos[u]; j++) {      //用从原点v到顶点u的路径替换从原点v到顶点w的路径
                        path[w][j] = path[u][j];
                        pos[w] = pos[u];
                    }
                }
            }
        }
        //打印顶点v到各顶点的最短路径
        displayPath(v, pos, path, dist);
    }
    /**
     * 打印一维数组
     * @param item
     */
    public static void display(int[] item, String name) {
        System.out.println();
        if(name!=null && name.length()>0){
            System.out.println("--------------" + name + "---------------");
        }
        for (int i = 0; i < item.length; i++) {
            System.out.print(item[i]==MaxValue?"OO," : item[i] + ",");
        }
        System.out.println();
        if(name!=null && name.length()>0) {
            System.out.println("--------------" + name + "---------------");
        }
    }

    /**
     * 利用标记数组s和dist找到一个通过u可以直接到达且尚未确定从原点到该顶点最短路径的一个顶点
     *
     * 若找到算法返回该顶点，否则返回-1
     * @param s 标记数组
     * @param dist 路径长度
     * @param u 顶点u
     * @return 成功返回该顶点，失败返回-1
     */
    private int searchVer(int[] s, int[] dist, int u) {


        /*int min = -1;
        for (int i = 0; i < Max; i++) {
            if(s[i] == 0 && u!=i && vertices[u][i] != MaxValue && dist[u] != MaxValue){
                min = i;
                break;
            }
        }

        if(min > -1){
            for (int i = min; i < Max; i++) {
                if(s[i] == 0 && vertices[u][min] > vertices[u][i] && u!=i){
                    min = i;
                }
            }
            if(dist[min] > dist[u] + vertices[u][min]){
                return min;
            }
        }*/
        return -1;
    }

    /**
     * 选择一个最小值
     * @param s 标记数组
     * @param dist 路径最小值
     * @return
     */
    private int minDist(int[] s, int[] dist) {
        int min = -1;
        for (int i = 0; i < Max; i++) {
            if(s[i]==0){
                // min赋初值为第一个未访问过的元素
                min = i;
                break;
            }
        }

        if(min > -1){
            for (int i = min; i < dist.length; i++) {
                if(s[i] == 0 && dist[min] > dist[i]){
                    min = i;
                }
            }
        }
        return min;
    }

    /**
     * 打印顶点v到各顶点的最短路径
     *
     * @param v 出发点v
     */
    public void displayPath(int v,int[] pos, int[][] path, int[] dist){
        System.out.println();
        for (int i = 0; i < Max; i++) {
            System.out.print((v+1)+"->"+(i+1)+":");
            for (int j = 0; j <=pos[i]; j++) {
                System.out.print((path[i][j]+1)+",");
            }
            System.out.println("最小路径权值->" + (dist[i]==MaxValue?"∞":dist[i]));
        }
        System.out.println();
    }

    /**
     * 求最短路径的floyed算法
     * @param g
     * @param D
     */
    private void floyd(int[][] g,int[][] D){
        for(int k = 0;k<Max;k++)
            for(int i = 0;i<Max;i++){
                for(int j = 0;j<Max;j++){
                    if(k == 0){
                        D[i][j] =g[i][j];
                    }else{
                        if(D[i][k] != MaxValue && D[k][j] != MaxValue && D[i][j]!=MaxValue){
//                            System.out.println(D[i][j]+","+D[i][k]+","+D[k][j]);
                            D[i][j] = D[i][j]>(D[i][k]+D[k][j])?D[i][k]+D[k][j]:D[i][j];
                        }
                    }
                }
            }
    }
    public void printgraph(int[][] g,int v){
        for(int i = 0;i<v;i++){
            for (int j = 0;j<v;j++){
                if(g[i][j] == MaxValue)
                    System.out.print("* ");
                else
                    System.out.print(g[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 多源最短路径问题，动态规划的思想
     * 运行算法floyed
     */
    public void runFloyed(){
        int[][]  graph = new int[Max][Max];
//        printgraph(this.vertices, Max);
        floyd(this.vertices, graph);
//        printgraph(graph,Max);
    }


    /**
     * 限制顶点的最小生成树算法
     *
     * 方法是：用普利姆方法求最短路径，但不是n个节点，而是 m<n 个节点
     *
     */
    public void primImpromve(){
        // 总共需要找到的顶点数
        int n = 6;

        // 最后两位数，一个存权值一个存第一位编号
        int[][] store = new int[Max][n+2];

        for (int i = 0; i < Max; i++) {
//            需找到 n-1 条边
            store[i][7]=i+1;
            minspantPrim(store[i], i, n-1);
        }
        SortUtil.sort(store, n + 1);
        displayMatrix(store, Max, n + 2);
    }

    /**
     * 打印二维数组
     * @param store
     */
    private void displayMatrix(int[][] store, int row, int col) {
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col-2; j++) {
                System.out.print(store[i][j]+"\t");
            }
            System.out.println("\t"+(store[i][col-1]<10?("0"+store[i][col-1]):store[i][col-1])+"\t"+store[i][col-2]);
        }
    }

    private TopoVertexLink minspantPrimOutPutVertexLink(){
        // 用于存放V-U中各顶点到U中各顶点的权值
        int lowcost[] = new int[Max];
        int mincost,k = 0;

        // 用于记录该边在U中的那个顶点
        int teend[] = new int[Max];
        lowcost[0]=0;
        teend[0]=0;

        // 用于存储生成过程中的边信息
        TopoVertexLink topoVertexLink = new TopoVertexLink(Max);

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
            // 添加一条边
            topoVertexLink.addEdge(teend[k], k, mincost);
            lowcost[k]=0; // 定点k加入到U中
            for (j = 0; j < Max; j++) {
                if(vertices[k][j] < lowcost[j]){
                    lowcost[j] = vertices[k][j];
                    teend[j] = k;
                }
            }
        }
        System.out.println();
        return topoVertexLink;
    }

    /**
     * 拓扑排序
     * 普利姆算法查找最短路径，拓扑排序算法打印拓扑结构
     */
    public void minspantPrimWithTopoSort(int m){
        TopoVertexLink topoVertexLink = minspantPrimOutPutVertexLink();
        topoVertexLink.topSort();
        display(topoVertexLink.outputs, "outputs");
    }

    /**
     * 输出 dot 字符串
     */
    public void toDot(){
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");
        sb.append("node [shape = circle];\n");
        for (int i = 0; i < Max; i++) {
            for (int j = 0; j < Max; j++) {
                if(i!=j){
                    if(vertices[i][j]!=MaxValue){
                        sb.append("    r"+i+" -> r" + j + " [label=\"" + vertices[i][j] + "\"];\n");
                    }
                }
            }
        }
        sb.append("}\n");
        System.out.println(sb);
    }
}
