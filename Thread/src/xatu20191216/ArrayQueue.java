package xatu20191216;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description:循环队列
 *
 * @author: KangWuBin
 * @Date: 2019/12/16
 * @Time: 20:51
 */
public class ArrayQueue {
    private int[] array = new int[10];
    private int size = 0;//有效元素个数
    private int front = 0;  //队头
    private int rear = 0;  //队尾

    public void put(int val) {
        if (size == array.length) {
            throw new RuntimeException("满了");
        }

        array[rear] = val;
        rear = (rear + 1) % array.length;
        /*
        array[rear++] = val;
        if (rear == array.length) {
            rear = 0;
        }
         */
        size++;
    }

    public int take() {
        if (size == 0) {
            throw new RuntimeException("空了");
        }

        /*
        int val = array[front++];
        if (front == array.length) {
            front = 0;
        }
        */
        int val = array[front];
        front = (front + 1) % array.length;
        size--;
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

        @Override
        public void run() {
            Random random = new Random(20191216);
            while (true) {
                int val = random.nextInt(100);
                try {
                    queue.put(val);
                } catch (RuntimeException e) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }
    }

    private static class Customer extends Thread {
        Customer() {
            super("消费者");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int val = queue.take();
                } catch (RuntimeException e) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.start();
        Customer customer = new Customer();
        customer.start();
    }
}
