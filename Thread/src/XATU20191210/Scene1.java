package XATU20191210;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/10
 * @Time: 9:17
 */
public class Scene1 {
    private static final long COUNT = 10_0000_0000;

    private static class MyThread extends Thread {
        @Override
        public void run() {
            long b = 0;
            for (int i = 0; i < COUNT; i++) {
                b += i;
            }
        }
    }

    /*1.多线程：*/
    private static void multiThread() throws InterruptedException {
        long begin = System.currentTimeMillis();
        MyThread thread = new MyThread();
        thread.start();
        long a = 0;
        for (int i = 0; i < COUNT; i++) {
            a += i;
        }
        thread.join();
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    /*2.单线程：*/
    private static void oneThread() {
        long begin = System.currentTimeMillis();
        long b = 0;
        for (int i = 0; i < COUNT; i++) {
            b += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    public static void main(String[] args) throws InterruptedException {
        multiThread();
        oneThread();
    }
}
