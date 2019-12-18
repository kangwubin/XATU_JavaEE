package xatu20191217;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 18:47
 */
public class BlockingQueueDemo {
    /*阻塞式队列:
     * 1.ArrayBlockingQueue需要传入初始容量；
     * 2.LinkedBlockingDeque 是没上限的；
     * 3.PriorityBlockingQueue
     * 4.如果想要阻塞，一定要调用take方法；*/
    static BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(10);
    static BlockingQueue<String> queue2 = new LinkedBlockingDeque<>();
    static BlockingQueue<String> queue3 = new PriorityBlockingQueue<>();

    private static class Customer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    int n = queue1.take();
                    long result = fin(n);
                    System.out.printf("fib(%d)=%d%n", n, result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private static long fin(int n) {
            if (n < 2) {
                return n;
            }
            return fin(n - 1) + fin(n - 2);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.start();
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入n:");
            int n = scanner.nextInt();
            queue1.put(n);
        }
    }
}
