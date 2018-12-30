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

    public Edge(int vertexIndex, int weight, Edge next) {
        this.vertexIndex = vertexIndex;
        this.weight = weight;
        this.next = next;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (vertexIndex != edge.vertexIndex) return false;
        if (weight != edge.weight) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vertexIndex;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertexIndex=" + vertexIndex +
                ", weight=" + weight +
                ", next=" + next +
                '}';
    }
}
