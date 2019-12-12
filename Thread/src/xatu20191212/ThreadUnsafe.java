package xatu20191212;

/**
 * Description:线程不安全的示例
 *
 * @author: KangWuBin
 * @Date: 2019/12/12
 * @Time: 10:14
 */
public class ThreadUnsafe {
    private static long n = 0;

    private static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 50000; i++) {
                //  synchronized (this)---因为运行的是5个线程，用this表示将锁加给当前的对象.
                //  synchronized (MyThread.class)
                synchronized (ThreadUnsafe.class) {
                    n++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            Thread t = new MyThread();
            t.start();
            threads[i] = t;
        }
        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }
        System.out.println(n);
    }
}
