package xatu20191216;

import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/16
 * @Time: 19:11
 */
public class Main {
    private static Object object = new Object();

    private static class AThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("A在扫地！");
            }
            try {
                //在调用wait()方法时：需要给调用它的对象加锁，不然会抛出异常；
                synchronized (object) {
                    object.wait();  /* 1.释放锁、2.更改状态、 3.放入等待集*/
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("A在擦桌子！");
            }
        }
    }

    public static void main(String[] args) {
        AThread aThread = new AThread();
        aThread.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入指令，让A去擦桌子！");
        scanner.nextLine();
        /*如果notify()出现在wait()之前，就会出现没有线程唤醒；*/
        synchronized (object) {
            //在调用 notify()方法时：需要给调用它的对象加锁，不然会抛出异常
            object.notify();
        }
    }
}
