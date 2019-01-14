package wuchaofei.top.linkList;

/**
 * hashKey接口
 * 标识对象必须实现hashKey方法,自己计算在散列中的位置
 * Created by cofco on 2019/1/14.
 */
public interface HashKey<T> {
    // 计算索引位置
    int hashKey();
}
