package xatu191228;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/28
 * @Time: 9:36
 */
public class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        // 建立监听 Socket
        ServerSocket serverSocket = new ServerSocket(8888);
        // 建立线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        // 循环着：读取请求-发送响应
        while (true) {
            // 等待客户端的连接（Chrome)
            Socket socket = serverSocket.accept();
            pool.execute(new Task(socket));
        }
    }
}
