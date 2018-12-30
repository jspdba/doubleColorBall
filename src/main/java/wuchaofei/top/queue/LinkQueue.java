package wuchaofei.top.queue;

/**
 * 链接队列数据结构
 * Created by cofco on 2018/12/30.
 */

public class LinkQueue {
    /**
     * 链接队列队头队尾
     */
    private Node front,rear;

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return front == null;
    }

    /**
     * 获取第一个队列中的值，不删除元素
     * @return
     */
    int getQueue(){
        if(isEmpty()){
            return front.getData();
        }
        return -1;
    }

    /**
     * 添加队头元素
     * @param item
     */
    public void addQueue(int item){
        Node node = new Node(item, null);
        if(isEmpty()){
            front = node;
            rear = front;
        }else{
            rear.setLink(node);
        }
        rear = node;
    }

    /**
     * 删除队列
     * 相当于出队
     */
    public int deleteQueue(){
        if(!isEmpty()){
            Node p = front;
            int result = p.getData();
            front = p.getLink();
            if(front == null){
                rear = front;
            }
            p = null;
            return result;
        }
       return -1;
    }

    /**
     * 删除队列
     * @return
     */
    public void destroyQueue(){
        while (front!=null){
            rear = front.getLink();
            front = null;
            front = rear;
        }
    }

    public Node getFront() {
        return front;
    }

    public void setFront(Node front) {
        this.front = front;
    }

    public Node getRear() {
        return rear;
    }

    public void setRear(Node rear) {
        this.rear = rear;
    }
}
