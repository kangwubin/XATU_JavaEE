package XATU20191210;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/10
 * @Time: 11:36
 */
public class ThreadDie {
    private static class Worker extends Thread {
        private volatile boolean running = true;

        public void quit() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                System.out.println("挖煤");
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Worker worker = new Worker();
            worker.start();
            Thread.sleep(10 * 1000);
            System.out.println("十万火急，停止挖煤，请速速回家！");
            worker.quit();
            worker.join();
            System.out.println("回家了！");
        }
    }
}
