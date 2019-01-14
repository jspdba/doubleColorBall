package wuchaofei.top.linkList;

/**
 * 线性链表数据节点
 * Created by cofco on 2019/1/14.
 */

public class LinkNode<T> {
//    线性链表节点数据域
    private T data;
//    线性链表节点指针域
    private LinkNode link;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkNode getLink() {
        return link;
    }

    public void setLink(LinkNode link) {
        this.link = link;
    }
}
