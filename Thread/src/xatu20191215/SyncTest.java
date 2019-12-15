package xatu20191215;

import java.util.Random;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/15
 * @Time: 9:40
 */
public class SyncTest {
    //index 是三个线程共享；
    private static int index = 0;

    private static class MyThread extends Thread {
        int[] array;

        MyThread(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            /*synchronized (MyThread.class)放在外面，只有一个线程在计算；*/
            while (index < array.length) {
                synchronized (MyThread.class) {//加锁，确保数据安全；
                    /*加if的判断，是因为在第一个线程计算完以后，index的值改变了；
                     * 第二个线程进入的前提是不忘自己初始的值；*/
                    if (index < array.length) {
                        array[index] = array[index] * 3;
                        index++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[10_0000];
        Random random = new Random(20191215);//给定随机种子，最终输出就是一致的。
       /* for (int i = 0; i < 20; i++) {
            System.out.println(random.nextInt());
        }*/
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        MyThread myThread1 = new MyThread(array);
        myThread1.start();
        MyThread myThread2 = new MyThread(array);
        myThread2.start();
        MyThread myThread3 = new MyThread(array);
        myThread3.start();
        myThread1.join();
        myThread2.join();
        myThread3.join();
        System.out.println(array[0]);
        System.out.println(array[3]);
        System.out.println(array[107]);
        System.out.println(array[323]);
        System.out.println(array[6666]);
        System.out.println(array[88888]);
        System.out.println(array[93192]);
    }
}
