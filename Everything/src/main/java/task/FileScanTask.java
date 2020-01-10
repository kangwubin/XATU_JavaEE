package task;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/5
 * @Time: 9:06
 */
public class FileScanTask {
    private static final ExecutorService POOL
            = Executors.newFixedThreadPool(4);//固定大小的线程池
    //    private static volatile int COUNT;//记录运行的线程数

    private static AtomicInteger COUNT = new AtomicInteger();

    private static CountDownLatch LATCH = new CountDownLatch(1);

    private FileScanCallback callback;

    public FileScanTask(FileScanCallback callback) {
        this.callback = callback;
    }

    /*启动根目录的扫描任务*/
    public void startScan(File root) {
       /* synchronized (this) {
            COUNT++;
        }*/
        COUNT.incrementAndGet();
        //root = new File("F:\\PycharmProjects");
        // list(root);
        POOL.execute(new Runnable() {
            @Override
            public void run() {
                list(root);
            }
        });
    }

    public void waitFinish() {
       /* synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            //中断所以线程池
            POOL.shutdown();        //调用每个线程稍微interrupted
            //POOL.shutdownNow();     //调用每个线程stop关闭
        }
    }

    public void list(File dir) {
        /*synchronized (this) {
            COUNT++;
        }*/
        if (!Thread.interrupted()) {
            try {
                //System.out.println(dir.getPath());
                callback.execute(dir);
                if (dir.isDirectory()) {
                    File[] children = dir.listFiles();
                    if (children != null && children.length > 0) {
                        for (File child : children) {
                            //list(child);
                            //启动子线程执行文件夹的扫描任务
                            if (child.isDirectory()) {
                           /* synchronized (this) {
                                COUNT++;
                            }*/
                                COUNT.incrementAndGet();
                        /*new Thread(new Runnable() {
                            @Override
                            public void run() {
                                list(child);
                            }
                        }).start();*/
                                POOL.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        list(child);
                                    }
                                });
                            } else {
                                // System.out.println(child.getPath());
                                callback.execute(child);
                            }
                        }
                    }
                }
            } finally {
            /*synchronized (this) {
                COUNT--;
                if (COUNT == 0) {
                    //notify()
                    this.notifyAll();
                }
            }*/
                //所有线程执行完毕
                if (COUNT.decrementAndGet() == 0) {
                    //通知
                    LATCH.countDown();
                }
            }
        }
    }

    /*测试代码*/
    public static void main(String[] args) throws InterruptedException {
       /* FileScanTask task = new FileScanTask();
        task.startScan(new File("F:\\【比特科技书籍推荐】Java研发方向"));
        synchronized (task) {
            task.wait();
        }
        System.out.println("执行完毕！");*/
    }
}
