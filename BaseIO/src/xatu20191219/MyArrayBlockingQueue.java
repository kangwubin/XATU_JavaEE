package xatu20191219;

/**
 * Description:要求：多生产者---多消费者(掌握)，但是效率不保证；
 * 可改进：
 * 1.锁（生产者和消费者都在竞争锁）
        生产者和消费者之间共享:size；
        生产者和生产者之间共享:size + rear；
        消费者和消费者之间共享:size + front；
 * 2.通知（全通知）：
 * 精准通知：生产者只通知生产者；
           消费者只通知消费者；
 *
 * @author: KangWuBin
 * @Date: 2019/12/19
 * @Time: 9:38
 */
public class MyArrayBlockingQueue {
    private int[] array;
    //front: 队首元素所在下标
    private int front;
    //rear : 下一个要插入元素的下标
    private int rear;
    //size : 已有元素的个数
    private volatile int size;

    public MyArrayBlockingQueue(int capacity) {
        array = new int[capacity];
        rear = 0;
        front = 0;
        size = 0;
    }

    private synchronized void put(int element) throws InterruptedException {
        while (size == array.length) {
            wait();
        }
        array[rear++] = element;
        if (rear == array.length) {
            rear = 0;
        }
        size++;
        /* 唤醒正在睡眠的生产者+消费者；
         * 因为无法保证只唤醒生产者；*/
        notifyAll();
    }

    private synchronized int take() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        int element = array[front++];
        if (front == array.length) {
            front = 0;
        }
        size--;
        /* 唤醒正在睡眠的生产者+消费者；
         * 因为无法保证只唤醒生产者；*/
        notifyAll();
        return element;
    }

    private int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
