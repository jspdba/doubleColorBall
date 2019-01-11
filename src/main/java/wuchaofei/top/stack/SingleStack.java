package wuchaofei.top.stack;

/**
 * 最简单的顺序存储结构的栈
 * Created by wuchaofei on 2019/1/11.
 */
public class SingleStack {
    // 栈顶指针
    private int top = -1;
    // 栈的大小
    private int size;
    // 栈中数据（int 类型）
    private int[] data;

    public SingleStack(int size) {
        this.size = size;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return data==null || top<0;
    }

    /**
     * 是否栈满
     * @return
     */
    public boolean isFull(){
        if(data.length == top+1){
            return true;
        }
        return false;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if(isEmpty()){
            return -1;
        }
        int result = data[top--];
        if(top == -1){
            data = null;
        }
        return result;
    }

    /**
     * 入栈
     * @param value
     * @return
     */
    public boolean push(int value){
        if(data == null){
            data = new int[size];
            data[++top] = value;
            return true;
        }
        if(isFull()){
           return false;
        }
        data[++top] = value;
        return true;
    }

    /**
     * 获取栈大小
     * @return
     */
    public int size(){
        if(top == -1 || data == null){
            return 0;
        }
        return top;
    }

    /**
     * copy一份当前的栈信息
     */
    public void copy(SingleStack stack){
        stack.top = this.top;
        if(stack.data == null){
            stack.data = new int[size];
        }
        if(top!=-1){
            for (int i = 0; i < top; i++) {
                stack.data[i]=this.data[i];
            }
        }
    }

    public void clear() {
        if(top != -1){
            top = -1;
        }
    }
}
