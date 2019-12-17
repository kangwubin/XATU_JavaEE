package xatu20191217;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 11:55
 */
public class MyThreadPool {
    private static class Worker extends Thread {
        private BlockingQueue<Runnable> queue = null;

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                Runnable command = null;
                try {
                    command = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                command.run();
            }
        }
    }

    private int corePoolSize = 10;
    private int currentPoolSize = 0;
    private List<Worker> workerList = new ArrayList<>();
    /*LinkedBlockingDeque:天生具备线程安全；*/
    private BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();

    public void execute(Runnable command) throws InterruptedException {
        if (currentPoolSize < corePoolSize) {
            Worker worker = new Worker();
            workerList.add(worker);
            currentPoolSize++;
            worker.start();
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
}
