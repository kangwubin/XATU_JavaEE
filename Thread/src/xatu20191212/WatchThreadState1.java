package xatu20191212;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/12
 * @Time: 9:32
 */
public class WatchThreadState1 {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(10_0000_0000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        System.out.println(thread.getState());
        thread.start();
        while (thread.isAlive()) {
            System.out.println(thread.getState());
        }
    }
}
