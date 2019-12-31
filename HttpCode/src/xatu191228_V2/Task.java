package xatu191228_V2;

import java.net.Socket;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/30
 * @Time: 18:02
 */
public class Task implements Runnable {
    private final Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
