package xatu20191217;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:实现简单的线程池---会
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 11:55
 */
public class MyThreadPool {
    private static class Worker extends Thread {
        private BlockingQueue<Runnable> queue = null;

        Worker(BlockingQueue<Runnable> queue) {
            super("我是劳动人民");
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Runnable command = queue.take();
                    command.run();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    private int corePoolSize = 10;//正式员工
    private int currentPoolSize = 0;//当前员工
    private List<Worker> workerList = new ArrayList<>();
    /*LinkedBlockingDeque:天生具备线程安全；*/
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public void execute(Runnable command) {
        if (currentPoolSize < corePoolSize) {
            Worker worker = new Worker(queue);
            worker.start();
            workerList.add(worker);
            currentPoolSize++;
        }

        queue.add(command);
    }

    public void shutDown() throws InterruptedException {
        for (Worker worker : workerList) {
            worker.interrupt();
        }
        for (Worker worker : workerList) {
            worker.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool = new MyThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("吃饭");
            }
        });
        Thread.sleep(10);
        pool.shutDown();
    }
}
