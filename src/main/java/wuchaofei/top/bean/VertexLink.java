package wuchaofei.top.bean;

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

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }
}
