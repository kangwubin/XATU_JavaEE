package xatu20191212;

/**
 * Description:
 * 代码能执行的前提：已经拿到了CPU
 *
 * @author: KangWuBin
 * @Date: 2019/12/12
 * @Time: 9:32
 */
public class WatchThreadState {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        System.out.println(thread.getState());
        thread.start();
        thread.interrupt();
        while (thread.isAlive()) {
            System.out.println(thread.getState());
        }
        System.out.println(thread.getState());
    }
}
