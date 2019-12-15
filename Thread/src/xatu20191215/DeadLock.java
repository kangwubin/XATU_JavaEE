package xatu20191215;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/15
 * @Time: 11:02
 */
public class DeadLock {
    private static Object aLock = new Object();
    private static Object bLock = new Object();

    private static class AThread extends Thread {
        @Override
        public void run() {
            synchronized (aLock) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (bLock) {
                    System.out.println("A:吃火锅！");
                }
            }
        }
    }

    private static class BThread extends Thread {
        @Override
        public void run() {
            synchronized (bLock) {
                synchronized (aLock) {
                    System.out.println("B:吃火锅！");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AThread aThread = new AThread();
        BThread bThread = new BThread();
        aThread.start();
        Thread.sleep(2000);
        bThread.start();
    }
}
