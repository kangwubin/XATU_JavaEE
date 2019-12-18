package xatu20191217;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/17
 * @Time: 19:11
 */
public class MyTimer2 {
    public static void main(String[] args) {
        Timer timer = new Timer();
//        timer.schedule();第一个参数为任务，第二个参数为延迟时间
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("5秒钟到了");
            }
        };
        timer.schedule(task, 5000);
    }
}
