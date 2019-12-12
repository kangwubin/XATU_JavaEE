package XATU20191210;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/10
 * @Time: 11:36
 */
public class ThreadDie2 {
    private static class Worker extends Thread {

        @Override
        public void run() {
            //!Thread.interrupted() == !isInterrupted()
            while (!Thread.interrupted()) {
                System.out.println("挖煤");
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    break;//捕获终止异常后，直接结束。
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Worker worker = new Worker();
            worker.start();
            Thread.sleep(10 * 1000);
            System.out.println("十万火急，停止挖煤，请速速回家！");
            worker.interrupt();//建议性终止
            worker.join();
            System.out.println("回家了！");
        }
    }
}
