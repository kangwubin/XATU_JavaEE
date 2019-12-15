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
    private static volatile Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
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
