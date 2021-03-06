package xatu20191215;

/* 面试重点：synchronized(可以保证原子性、可见性、代码重排序性)；
 * volatile(保证可见性、代码重排序性、不会导致线程切换、保证long/double的原子性)；
 * 所谓的保证原子性是保证常量给变量赋值；*/
public class PrintInOrder {
    private volatile int i = 0;

    public void first() {
        synchronized (this) {
            if (i == 0) {
                System.out.println("one");
                i = 1;
            }
        }
    }

    public void second() {
        synchronized (this) {
            if (i == 1) {
                System.out.println("two");
                i = 2;
            }
        }
    }

    public void third() {
        synchronized (this) {
            if (i == 2) {
                System.out.println("three");
                i = 0;
            }
        }
    }

    private static class PrintOne extends Thread {
        PrintInOrder object;

        PrintOne(PrintInOrder object) {
            this.object = object;
        }

        @Override
        public void run() {
            while (true) {
                object.first();
            }
        }
    }

    private static class PrintTwo extends Thread {
        PrintInOrder object;

        PrintTwo(PrintInOrder object) {
            this.object = object;
        }

        @Override
        public void run() {
            while (true) {
                object.second();
            }
        }
    }

    private static class PrintThree extends Thread {
        PrintInOrder object;

        PrintThree(PrintInOrder object) {
            this.object = object;
        }

        @Override
        public void run() {
            while (true) {
                object.third();
            }
        }
    }

    public static void main(String[] args) {
        PrintInOrder object = new PrintInOrder();
        Thread t1 = new PrintOne(object);
        t1.start();
        Thread t2 = new PrintTwo(object);
        t2.start();
        Thread t3 = new PrintThree(object);
        t3.start();
    }
}
