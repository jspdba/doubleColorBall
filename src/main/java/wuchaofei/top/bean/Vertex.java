package wuchaofei.top.bean;

/**
 * 图的顶点数据结构
 * Created by cofco on 2018/12/29.
 */

public class Vertex {
    /**
     * 顶点的入度
     * 用于AOV网的存储结构，用在拓扑排序算法中
     */
    int indegree;
    /**
     * 顶点的数据信息（代表红球）
     */
    int num;
    /**
     * 指向第一条边对应的边节点
     */
    Edge link;

    public Vertex(){
    }

    public Vertex(int num, Edge link){
        this.num = num;
        this.link = link;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Edge getLink() {
        return link;
    }

    public void setLink(Edge link) {
        this.link = link;
    }

    public int getIndegree() {
        return indegree;
    }

    public void setIndegree(int indegree) {
        this.indegree = indegree;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "indegree=" + indegree +
                ", num=" + num +
                ", link=" + link +
                '}';
    }
}
