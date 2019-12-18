package xatu20191215;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/15
 * @Time: 11:39
 */
public class Singleton {
    private int field = 0;

    public Singleton() {
        field = 100;
    }

    /* 面试手撕代码：懒汉模式-多线程版-二次判断-性能高*/
    //对象初始化为null
    /* volatile:为了保证对象初始化的正确顺序；防止对象初始化时出现未初始化的现象；
     * instance = new Singleton();*/
    private static volatile Singleton instance = null;

    private static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                /* 加二次判断是因为某个线程加锁后，会初始化对象，然后会改变instance的值；
                 * 第二个线程加完锁以后，就需要判断instance是不是还是当初的值，有没发生改变；*/
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Singleton s = Singleton.getInstance();
                System.out.println(s + ":" + s.field);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new MyThread().start();
        }
    }
}
