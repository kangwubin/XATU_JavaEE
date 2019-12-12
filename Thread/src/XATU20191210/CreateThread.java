package XATU20191210;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/10
 * @Time: 10:10
 */
public class CreateThread {
    private static long COUNT = 1_000_0000;

    //1. extends Thread创建线程
    private static class MyThread extends Thread {
        @Override
        public void run() {
            long ret = 0;
            for (int i = 0; i < COUNT; i++) {
                ret += i;
                System.out.println(ret);
            }
        }
    }

    //2. implements Runnable创建线程
    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            long ret = 0;
            for (int i = 0; i < COUNT; i++) {
                ret += i;
                System.out.println(ret);
            }
        }
    }

    public static void main(String[] args) {
        // 直接通过myThread启动线程
        MyThread myThread = new MyThread();
        myThread.start();

        // 将MyRunnable作为Thread类参数，启动线程。
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        MyThread myThread1 = new MyThread();
        Thread thread1 = new Thread(myThread1);
        thread1.start();

    }
}
