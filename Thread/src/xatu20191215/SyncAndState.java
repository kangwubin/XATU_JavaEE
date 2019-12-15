package xatu20191215;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/15
 * @Time: 9:22
 */
public class SyncAndState {
    /* 1.抢锁的前提是先抢CPU；
     * 2.抢锁失败后：
     *   (1)状态从RUNNABLE修改为BLOCKED；
     *   (2)线程从就绪队列移动到该锁的阻塞队列上；
     *   (3)从开始请求锁到最终抢到锁，经历了复杂的过程；*/
    private synchronized void method() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("" + i + ":" + Thread.currentThread().getName());
        }
    }

    private static class MyThread extends Thread {
        private SyncAndState object;

        public MyThread(SyncAndState object, String name) {
            super(name);
            this.object = object;
        }

        @Override
        public void run() {
            object.method();
        }
    }

    public static void main(String[] args) {
        SyncAndState object = new SyncAndState();
        MyThread myThread = new MyThread(object, "我是子线程");
        myThread.start();
        object.method();
    }
}
