package wuchaofei.top.bean;

import sun.reflect.misc.FieldUtil;
import wuchaofei.top.utils.FileUtil;

import java.util.ArrayList;
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
        List<ArrayList> result = FileUtil.readTextFile("D:\\zhongliang\\doubleColorBall\\src\\main\\resources\\balls.txt");
        System.out.println(result);
    }
}
