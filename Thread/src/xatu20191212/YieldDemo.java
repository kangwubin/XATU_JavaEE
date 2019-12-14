package xatu20191212;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/12
 * @Time: 9:50
 */
public class YieldDemo {
    private static class MyThread extends Thread {
        private final String message;

        //Thread类中有name，就可以使用super调用
        MyThread(String name, String message) {
            super(name);
            this.message = message;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":" + message);
            }
        }
    }

    private static class MyThread2 extends Thread {
        private final String message;

        //Thread类中有name，就可以使用super调用
        MyThread2(String name, String message) {
            super(name);
            this.message = message;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":" + message);
                yield();// yield() 只是让出 CPU，并不会改变自己的状态
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("自私的", "Sing Songs");
        MyThread2 myThread2 = new MyThread2("不是那么自私的", "Sing Songs");
        myThread1.start();
        myThread2.start();
    }
}
