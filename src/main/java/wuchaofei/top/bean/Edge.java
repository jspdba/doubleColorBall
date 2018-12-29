package wuchaofei.top.bean;

/**
 * 边的数据结构
 * Created by cofco on 2018/12/29.
 */

public class Edge {
    /**
     * 该边的终止顶点在顶点节点中的位置
     */
    int vertexIndex;
    /**
     * 该边的权值
     */
    int weight;

    /**
     * 指向下一个边节点
     */
    Edge next;

    public int getVertexIndex() {
        return vertexIndex;
    }

    public void setVertexIndex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }
}
