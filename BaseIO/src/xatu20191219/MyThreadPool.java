package xatu20191219;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:创建线程池需要用的是阻塞式队列(BlockingQueue)与任务(Runnable)---(+++++)
 *
 * @author: KangWuBin
 * @Date: 2019/12/19
 * @Time: 9:38
 */
public class MyThreadPool {
    private static class Worker extends Thread {
        private BlockingQueue<Runnable> queue = null;

        Worker(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Runnable command = queue.take();
                    command.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private int corePoolSize = 10;//正式员工
    private int currentPoolSize = 0;//当前员工
    private ArrayList<Worker> workers = new ArrayList();
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public void execute(Runnable command) {
        if (currentPoolSize < corePoolSize) {
            Worker worker = new Worker(queue);
            worker.start();
            workers.add(worker);
            currentPoolSize++;
        }
        /*queue.put:会抛异常；queue.add：不会抛异常；*/
        queue.add(command);
    }

    public void shutDown() throws InterruptedException {
        for (Worker worker : workers) {
            worker.interrupt();
        }
        for (Worker worker : workers) {
            worker.join();
        }
    }
}
