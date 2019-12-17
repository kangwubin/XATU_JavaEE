package xatu20191217;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Description:循环队列
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 09:51
 */
public class ArrayQueue {
    private int[] array = new int[10];
    private volatile int size = 0;   // 有效元素个数
    private int front = 0;
    private int rear = 0;

    /* synchronized：只锁size，性能更高；
     * synchronized：锁整个方法，性能低；*/
    private void put(int val) {
        if (size == array.length) {
            throw new RuntimeException("满了");
        }

        array[rear] = val;
        rear = (rear + 1) % array.length;
        synchronized (this) {
            size++;
        }
    }

    private int take() {
        if (size == 0) {
            throw new RuntimeException("空了");
        }
        int val = array[front];
        front = (front + 1) % array.length;
        synchronized (this) {
            size--;
        }
        return val;
    }

    public int getSize() {
        return size;
    }

    private static ArrayQueue queue = new ArrayQueue();

    private static class Producer extends Thread {
        Producer() {
            super("生产者");
        }

        PrintWriter printWriter;

        {
            try {
                printWriter = new PrintWriter("生产了.txt", "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            Random random = new Random(20191216);
            for (int i = 0; i < 1000; i++) {
                int val = random.nextInt(100);
                printWriter.println(val);
                do {
                    try {
                        queue.put(val);
                        break;
                    } catch (RuntimeException e) {
                    }
                } while (true);
            }
            printWriter.close();
        }
    }

    private static class Customer extends Thread {
        PrintWriter printWriter;

        Customer() {
            super("消费者");
        }

        {
            try {
                printWriter = new PrintWriter("消费了.txt", "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                do {
                    try {
                        int val = queue.take();
                        printWriter.println(val);
                        break;
                    } catch (RuntimeException e) {
                    }
                } while (true);
            }
            printWriter.close();
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.start();
        Customer customer = new Customer();
        customer.start();
    }
}
