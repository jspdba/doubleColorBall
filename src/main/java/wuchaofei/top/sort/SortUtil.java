package wuchaofei.top.sort;

import wuchaofei.top.bean.VertexMatrix;

/**
 * 排序算法
 * Created by cofco on 2019/1/3.
 */

public class SortUtil {
    /**
     * 插入排序
     * 算法只针对不同元素的插入
     * 本算法一次插入两个值
     */
    public static void insertSort(int[] arr,int start,int end, int size){
        if(arr[size-1]!=0){
            System.out.println("数组已满，请检查");
            return;
        }

        if(start>end){
            int temp = start;
            start = end;
            end = temp;
        }

        // 确定数组长度（除去0后）
        int pos = 0;

        // 直接插入
        if(arr[1]==0){
            arr[0] = start;
            arr[1] = end;
            return;
        }
        // 0都不包含，2包含前者，1包含后者，3都包含
        int containsCase = 0;
        // 找到数组长度
        for (int i = 0; i < size; i++) {
            if(arr[i]==start){
                containsCase += 2;
            }else if(arr[i]==end){
                containsCase += 1;
            }
            if(arr[i]!=0){
                pos ++;
            }else{
                break;
            }
        }

        if(containsCase == 4){
            return;
        }else if(containsCase == 1){
            insertOne(arr, pos, start);
        }else if(containsCase == 2){
            insertOne(arr, pos, end);
        }else{
            insertOne(arr, pos, start);
            insertOne(arr, pos+1, end);
        }
    }

    /**
     * 插入一个值
     * @param arr
     */
    private static void insertOne(int[] arr, int size, int value){
        int i = size-1;
        if(arr[size-1]<value){
            arr[i+1] = value;
            return;
        }
        for (; i>=0; ) {
            if(arr[i] > value){
                arr[i+1] = arr[i--];
            }else{
                break;
            }
        }
        arr[i+1] = value;
    }

    /**
     * 对二位数组按某一列进行排序
     * @param store
     * @param n 第几列
     */
    public static void sort(int[][] store, int n) {
        int size = store.length;
        int[] arr = new int[size];
        for (int i = 0; i < store.length; i++) {
            arr[i] = store[i][n-1];
        }
        quick(arr, 0, size-1, store);
    }

    /**
     * 快速排序
     */
    private static void quick(int[] k, int s, int t, int[][] store){
        int i = 0,j = 0;

        if(s<t){
            i=s;
            j=t+1;
            while(true){
                do {
                    i++;
                }while(!(k[s]<=k[i]||i==t));

                do {
                    j--;
                }while(!(k[s]>=k[j]||j==s));
                if(i<j){
                    swap(i,j, store, k);
                }else{
                    break;
                }
            }
            swap(s, j, store, k);
            quick(k,s,j-1, store);
            quick(k,j+1,t, store);
        }
    }

    /**
     * 交换两行的数据
     * @param i
     * @param j
     */
    private static void swap(int i, int j, int[][] store, int[] arr) {
        if(i==j){
            System.out.println("出错了，i,j="+i+","+j);
            return;
        }

        int[] temp = new int[store[0].length];
        for (int k = 0; k < temp.length; k++) {
            temp[k] = store[i][k];
            store[i][k] = store[j][k];
            store[j][k]=temp[k];
        }

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
