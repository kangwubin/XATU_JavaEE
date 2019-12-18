package xatu20191217;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:多生产者多消费者模式(+++++)，必须掌握。
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 09:51
 */
public class ArrayQueue2 {
    private int[] array = new int[10];
    private volatile int size = 0;   // 有效元素个数
    private int front = 0;
    private int rear = 0;

    /*多生产者多消费者模式，必须保证整个过程；即在put方法中，锁整个过程才能保证正确；*/
    private synchronized void put(int val) throws InterruptedException {
        while (size == array.length) {
            /*中断(interrupted)和虚假唤醒(wait)是可能的，并且该方法应该始终在循环中使用*/
            wait();
        }
        array[rear] = val;
        rear = (rear + 1) % array.length;
        size++;
        // notify();    // notify()---唤醒单个线程；
        notifyAll();    //notifyAll()---唤醒多个线程
    }

    private synchronized int take() throws InterruptedException {
        while (size == 0) {
            /*中断(interrupted)和虚假唤醒(wait)是可能的，并且该方法应该始终在循环中使用*/
            wait();
        }
        int val = array[front];
        front = (front + 1) % array.length;
        size--;
        // notify();    // notify()---唤醒单个线程；
        notifyAll();    //notifyAll()---唤醒多个线程
        return val;
    }

    private int getSize() {
        return size;
    }

    private static ArrayQueue2 queue = new ArrayQueue2();

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
                try {
                    queue.put(val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                try {
                    int val = queue.take();
                    printWriter.println(val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printWriter.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        producer.start();
        Customer customer = new Customer();
        customer.start();

        while (customer.isAlive()) {
            //如果customer还活着，就获取队列中元素的个数
            System.out.println(queue.getSize());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
