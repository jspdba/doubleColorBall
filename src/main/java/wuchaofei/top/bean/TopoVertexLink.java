package wuchaofei.top.bean;

/**
 * 拓扑排序使用的邻接表结构
 * Created by cofco on 2019/1/2.
 */

public class TopoVertexLink {
    // 顶点个数
    int max;
    // 图的邻接表存储结构的所有顶点
    Vertex[] vertexes;

    // 拓扑排序过程中输出的顶点
    int[] outputs;

    public static final int MaxValue=Integer.MAX_VALUE;

    public TopoVertexLink(int max) {
        this.max = max;
        vertexes = new Vertex[max];
        outputs = new int[max];

        // 创建顶点，并赋初值
        for (int i = 0; i < max; i++) {
            this.vertexes[i] = new Vertex(i+1, null);
        }
    }

    /**
     * 添加一条边
     * @param start 边的起点顶点
     * @param end   边的终点顶点
     * @param weight 边的权值
     */
    void addEdge(int start, int end, int weight){
        // 创建一条边
        Edge edge = new Edge(end,weight,null);
        //找到顶点存入第一条边
        Vertex vertex = this.vertexes[start];
        // 改顶点的入度加1
        this.vertexes[end].indegree ++;

        Edge p = vertex.getLink();
        if(p==null){
            vertex.setLink(edge);
        }else{
            // 找到插入位置,进行插入
            Edge r = null;// p的前一个节点
            do{
                // 找到要插入位置
                if(edge.getVertexIndex()<p.getVertexIndex()){
                    break;
                }
                r = p;
                p= p.getNext();
            }while(p!=null);
            // 直接插入到末尾
            if(p==null){
                if(r.getVertexIndex()==edge.getVertexIndex()){
                    r.setWeight(r.getWeight()+1);
                }else{
                    r.next = edge;
                }
            }else{
                // 插入顶点与第一条边之间
                if(r == null){
                    Edge temp = vertex.getLink();
                    edge.setNext(temp);
                    vertex.setLink(edge);
                }else{
                    if(r.getVertexIndex()==edge.getVertexIndex()){
                        r.setWeight(r.getWeight()+1);
                    }else{
                        // 插入到r与p之间
                        Edge temp = p;
                        edge.setNext(p);
                        r.setNext(edge);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        sb.append("VertexLink=[\n");
        for (int i = 0; i < vertexes.length; i++) {
            Vertex vertex = vertexes[i];
            sb.append(vertex.getNum()+":");
            Edge p = vertex.getLink();
            if(p==null){
                sb.append("[]\n");
            }else{
                sb.append("["+(p.getVertexIndex()+1)+","+p.getWeight()+"]");
                p = p.getNext();
                while (p!=null){
                    sb.append(",["+(p.getVertexIndex()+1)+","+p.getWeight()+"]");
                    p = p.getNext();
                };
                sb.append("\n");
            }
        }
        sb.append("]\n");
        return sb.toString();
    }

    /**
     * 拓扑排序算法
     *
     * 1. 将所有入度为0的顶点（indegree域内为0）压入链接栈
     * 2. 若堆栈不空，从占中退出栈顶元素输出，并把从该顶点引出的所有的有向边删去，同时分别把该顶点直接指向的各个邻接顶点的入度减1
     * 3. 将新的入度为0的顶点压入堆栈。
     * 4. 重复步骤2和3，直到输出全部顶点或者图中剩余的顶点中不再有入度为0的顶点为止
     *
     */
    public void topSort(){
        Edge p;

//        堆栈顶点
        int top = -1,j,k;

        for (int i = 0; i < max; i++) {
            if(vertexes[i].indegree == 0){
                vertexes[i].setIndegree(top);
                top = i;
            }
        }

        for (int i = 0; i <max; i++) {
            if(top == -1){
                System.out.println("\n网中存在回路！");
                break;
            }
            j = top;
            top = vertexes[top].getIndegree(); // 退出栈顶元素
            outputs[i] = vertexes[i].getNum(); // 输出一个顶点
            p=vertexes[i].getLink();

            while(p!=null){
                k = p.getVertexIndex();// "删除"一条由j发出的边
                vertexes[k].indegree--; //当前输出点的邻接点的入度减1
                if(vertexes[k].indegree==0){
                    vertexes[k].setIndegree(top);
                    top = k;
                }
                p = p.getNext(); //找到下一个邻接点（边结点）
            }
        }
    }

    /**
     * 模拟拓扑排序算法，取其中的m个顶点的最短路径
     *
     * 1. 将所有入度为0的顶点（indegree域内为0）压入链接栈
     * 2. 若堆栈不空，从占中退出栈顶元素输出，并把从该顶点引出的所有的有向边删去，同时分别把该顶点直接指向的各个邻接顶点的入度减1
     * 3. 将新的入度为0的顶点压入堆栈。
     * 4. 重复步骤2和3，直到输出全部顶点或者图中剩余的顶点中不再有入度为0的顶点为止
     *
     */
    public void minspantTopSort(){
        Edge p;

        // 标记数组，用于记录哪些顶点已经“删除”
        int[] flag = new int[max];
        // 用于记录最小权值（仅用作权值为正数的情况）
        int mincost = MaxValue ;
        int minVertex = -1;
        // 是否是该顶点的最后一条边
        boolean lastEdge = false;

        /**
         * 找出权值最小的边
         *
         * 此方法废弃不再继续写，有问题
         */
        for (int i = 0; i <max; i++) {
            p = vertexes[i].getLink();
            if(flag[i]==0 && p!=null){
                if(vertexes[i].indegree == 0){
                    if(p.getNext()==null){

                    }
                    /*while (true){
                        if(p.getNext()!=null){
                            p=p.getNext();
                        }
                    }*/
                }else{

                }


            }
        }
        // 找到要删除的边了，删除边对应的顶点
        if(minVertex>-1){
            flag[minVertex] = -1;


        }

       /* int top = -1,k;

        for (int i = 0; i <max; i++) {
            top = vertexes[top].getIndegree(); // 退出栈顶元素
            outputs[i] = vertexes[i].getNum(); // 输出一个顶点
            p=vertexes[i].getLink();

            while(p!=null){
                k = p.getVertexIndex();// "删除"一条由j发出的边
                vertexes[k].indegree--; //当前输出点的邻接点的入度减1
                if(vertexes[k].indegree==0){
                    vertexes[k].setIndegree(top);
                    top = k;
                }
                p = p.getNext(); //找到下一个邻接点（边结点）
            }
        }*/
    }
}
