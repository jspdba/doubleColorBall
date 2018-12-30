package wuchaofei.top.bean;

import sun.reflect.misc.FieldUtil;
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
    public static final int Max=33;
    /**
     * 图的所有顶点
     */
   private Vertex[] vertices = new Vertex[Max];

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

    public void init(){
        // 创建33个顶点，并赋初值
        for (int i = 0; i < Max; i++) {
            this.vertices[i] = new Vertex(i+1, null);
        }
        // 读取边信息
        List<ArrayList<Integer>> result = FileUtil.readTextFile("D:\\zhongliang\\doubleColorBall\\src\\main\\resources\\balls.txt");
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
                        if(r==null){
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
}
