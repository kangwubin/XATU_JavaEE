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

    /*
     * 任何代码可以运行的前提是：该代码所在的线程被调度到CPU上；*/
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.interrupt();
        /*isAlive():除过new和TERMINATED状态；*/
        while (thread.isAlive()) {
            System.out.println(thread.getState());
        }
        System.out.println(thread.getState());
    }
}
