package xatu20191217;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 20:16
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(30);
        ExecutorService pool = new ThreadPoolExecutor(10,
                20, 5, TimeUnit.SECONDS, queue);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("吃饭");
            }
        });
//        pool.submit()
        pool.shutdown();//可能会等待线程执行完
//        pool.shutdownNow();//会立马关掉
//        Executors.newFixedThreadPool();
    }
}
