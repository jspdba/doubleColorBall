package wuchaofei.top.bean;

/**
 * 图的顶点数据结构
 * Created by cofco on 2018/12/29.
 */

public class Vertex {
    /**
     * 顶点的数据信息（代表红球）
     */
    int num;
    /**
     * 指向第一条边对应的边节点
     */
    Edge link;

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
}
