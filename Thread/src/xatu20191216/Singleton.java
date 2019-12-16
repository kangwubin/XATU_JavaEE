package xatu20191216;

/**
 * Description:重要(+++++)：面试手撕单例模式代码
 *
 * @author: KangWuBin
 * @Date: 2019/12/16
 * @Time: 18:55
 */
public class Singleton {
    /*写一个懒汉模式的单例(保证线程安全)*/
    private static volatile Singleton instance = null;

    // private Singleton()---不让外部类构造对象
    private Singleton() {
    }

    /* synchronized不修饰方法是为了提高效率；
     * 二次判断是判断是否已经创建了对象；*/
    private static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    /* volatile关键字是为了保证赋值语句的重排序；
                     * synchronized 是保证了局部代码重排序；*/
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
