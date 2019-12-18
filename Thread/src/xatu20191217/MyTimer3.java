package xatu20191217;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Description:创维面试题---会
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 19:18
 */
public class MyTimer3 {
    private static class MyTimerTask implements Comparable<MyTimerTask> {
        long runAtTime;     //任务执行的时间--在这个时间必须执行任务
        Runnable target;    //任务

        public MyTimerTask(long delay, Runnable target) {
            this.runAtTime = System.currentTimeMillis() + delay;
            this.target = target;
        }

        @Override
        public int compareTo(MyTimerTask o) {
            if (runAtTime < o.runAtTime) {
                return -1;
            } else if (runAtTime == o.runAtTime) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    private PriorityBlockingQueue<MyTimerTask> queue = new PriorityBlockingQueue<>();
    Thread worker = new Worker();

    private class Worker extends Thread {
        @Override
        public void run() {
            //MyTimer.this.queue
            while (true) {
                try {
                    MyTimerTask task = queue.take();
                    if (task.runAtTime <= System.currentTimeMillis()) {
                        task.target.run();
                    } else {
                        queue.put(task);
                        synchronized (this) {
                            wait(task.runAtTime - System.currentTimeMillis());
                        }
                        //Thread.sleep(task.runAtTime - System.currentTimeMillis());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    MyTimer3() {
        worker.start();
    }

    public void schedule(Runnable target, long delay) {
        MyTimerTask task = new MyTimerTask(delay, target);
        queue.put(task);
        synchronized (this) {
            notify();
        }
    }

    public static void main(String[] args) {
        Runnable target = new Runnable() {
            @Override
            public void run() {
                System.out.println("5 秒后");
            }
        };
        MyTimer3 timer = new MyTimer3();
        timer.schedule(target, 5000);
    }
}
