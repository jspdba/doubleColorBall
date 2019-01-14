package wuchaofei.top.hashTable;

import wuchaofei.top.linkList.HashKey;
import wuchaofei.top.linkList.LinkNode;

/**
 * 商品的散列表
 * 基于数组 + 线性链表
 * 按商品价格计算key
 * Created by cofco on 2019/1/14.
 */

public class LinkedHashProduct<T extends HashKey> {
//    散列表长度
    private int size = 1;
//    散列表数据
    private LinkNode<T>[] datas;

    public LinkedHashProduct(int size) {
        this.size = size;
        this.datas = new LinkNode[size];
    }

    /**
     * 添加元素
     * @return
     */
    public boolean add(T data){
        int index = data.hashKey();
        if(isEmptyNode(index)){
            LinkNode node = new LinkNode<T>();
            node.setData(data);
            datas[index] = node;
        }else{
            // 添加到链表末尾
            LinkNode<T> node = datas[index];
            // q代表前一个节点
            LinkNode p = null,q = null;
            do{
                q = p;
                p = node.getLink();
            }while (p!=null);

            LinkNode newNode = new LinkNode<T>();
            newNode.setData(data);
            q.setLink(newNode);
        }
        return true;
    }

    /**
     * 检测当前节点是不是空节点
     * @param index
     * @return
     */
    public boolean isEmptyNode(int index){
        if(datas[index] == null){
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinkNode<T>[] getDatas() {
        return datas;
    }

    public void setDatas(LinkNode<T>[] datas) {
        this.datas = datas;
    }
}
