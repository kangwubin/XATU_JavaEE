package XATU20191209;

/**
 * Description:
 * 1.cmd下查看进程的命令：jconsole.
 *
 * @author: KangWuBin
 * @Date: 2019/12/9
 * @Time: 21:23
 */
public class Main extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("这是启动起来的线程！");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
        while (true) {
            System.out.println("Main线程！");
        }
    }
}
