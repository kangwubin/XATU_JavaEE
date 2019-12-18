package xatu20191217;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 19:06
 */
public class MyTimer {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("5秒钟到了");
            }
        });
        thread.start();
        while (true) {
            System.out.println("main在一直干活");
        }
    }
}
