package wuchaofei.top.bean;

import sun.reflect.misc.FieldUtil;
import wuchaofei.top.queue.LinkQueue;
import wuchaofei.top.utils.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图的邻接表存储结构
 * Created by cofco on 2018/12/29.
 */

public class VertexLink {
    /**
     * 定义最大顶点数
     */
    public static final int Max = 33;
    /**
     * 图的所有顶点
     */
   private Vertex[] vertices = new Vertex[Max];

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
   private static VertexLink vertexLink = new VertexLink();

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    /**
     * 获取一个图的实例
     * @return
     */
    public static VertexLink getInstance(){
        return vertexLink;
    }

    public VertexLink getVertexLink() {
        return vertexLink;
    }

    public void setVertexLink(VertexLink vertexLink) {
        this.vertexLink = vertexLink;
    }

    /**
     * 初始化图的邻接矩阵表示
     */
    public void init(){
        // 创建33个顶点，并赋初值
        for (int i = 0; i < Max; i++) {
            this.vertices[i] = new Vertex(i+1, null);
        }
        // 读取边信息
        List<ArrayList<Integer>> result = FileUtil.readTextFile("D:\\zhongliang\\doubleColorBall\\src\\main\\resources\\balls.txt");

//        对数据进行图的邻接表存储
        for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> line = result.get(i);
            for (int j = 0; j < line.size()-1; j++) {
                // 创建一条边
                Edge edge = new Edge(line.get(j+1)-1,1,null);
                //找到顶点存入第一条边
                Vertex vertex = this.vertices[line.get(j)-1];

                Edge p = vertex.getLink();
                if(p==null){
                    vertex.setLink(edge);
                }else{
                    // 找到插入位置,进行插入
                    Edge r = null;// p的前一个节点
                    do{
                        // 找到要插入位置
                        if(edge.getVertexIndex()<p.getVertexIndex()){
                            break;
                        }
                        r = p;
                        p= p.getNext();
                    }while(p!=null);
                    // 直接插入到末尾
                    if(p==null){
                        if(r.getVertexIndex()==edge.getVertexIndex()){
                            r.setWeight(r.getWeight()+1);
                        }else{
                            r.next = edge;
                        }
                    }else{
                        // 插入顶点与第一条边之间
                        if(r == null){
                            Edge temp = vertex.getLink();
                            edge.setNext(temp);
                            vertex.setLink(edge);
                        }else{
                            if(r.getVertexIndex()==edge.getVertexIndex()){
                                r.setWeight(r.getWeight()+1);
                            }else{
                                // 插入到r与p之间
                                Edge temp = p;
                                edge.setNext(p);
                                r.setNext(edge);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        sb.append("VertexLink=[\n");
        for (int i = 0; i < vertices.length; i++) {
            Vertex vertex = vertices[i];
            sb.append(vertex.getNum()+":");
            Edge p = vertex.getLink();
            if(p==null){
                sb.append("[]\n");
            }else{
                sb.append("["+(p.getVertexIndex()+1)+","+p.getWeight()+"]");
                p = p.getNext();
                while (p!=null){
                    sb.append(",["+(p.getVertexIndex()+1)+","+p.getWeight()+"]");
                    p = p.getNext();
                };
                sb.append("\n");
            }
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
        // 第一条边
        Edge edge = vertices[v].getLink();
        if(edge != null){
            do{
                if(visited[edge.getVertexIndex()]==0){
                    return edge.getVertexIndex();
                }
                edge=edge.getNext();
            }while(edge!=null);
        }
        return -1;
    }

    /**
     * 查找顶点vertex的第一个邻接点，失败返回-1
     * @param vertex 顶点下标
     * @return
     */
    private int firstVertex(int vertex) {
        Edge firstVertext = vertices[vertex].getLink();
        if(firstVertext!=null){
            return firstVertext.getVertexIndex();
        }
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


}
