package XATU20191210;

import java.util.Scanner;

/**
 * Description:
 * 1.斐波那契---面试高频点
 *
 * @author: KangWuBin
 * @Date: 2019/12/10
 * @Time: 9:36
 */
public class Scene2 {
    private static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    private static class MyThread extends Thread {
        private int n;

        //在线程里面传参需要使用构造方法，因为在run方法里面不能穿入参数.
        public MyThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            //将计算斐波那契计算放入线程当中，可以避免等待状态。
            long ret = fib(n);
            System.out.printf("fib(%d)的计算结果为%d%n", n, ret);
        }
    }

    //main方法为主线程
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要计算的n: ");
            int n = scanner.nextInt();
            //调用线程
            Thread thread = new MyThread(n);
            //启动线程
            thread.start();
        }
    }
}
