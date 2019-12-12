package XATU20191210;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/10
 * @Time: 11:16
 */
public class ThreadFields {
    public static void main(String[] args) {
        /*1. Thread.currentThread();--获取当前线程的Thread*/
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getId());
        System.out.println(currentThread.getName());
        System.out.println(currentThread.getPriority());
        System.out.println(currentThread.getState());
        System.out.println(currentThread.isDaemon());
        System.out.println(currentThread.isAlive());
        System.out.println(currentThread.isInterrupted());
    }
}
