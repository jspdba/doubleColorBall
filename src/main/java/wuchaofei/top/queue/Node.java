package wuchaofei.top.queue;

/**
 * 链接队列 节点数据结构
 * Created by cofco on 2018/12/30.
 */

public class Node {
    /**
     * 数据
     */
    int data;
    /**
     * 链接
     */
    Node link;

    public Node(int data, Node link) {
        this.data = data;
        this.link = link;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    /**
     * 只判断数据元素相等
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (data != node.data) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data;
    }
}
