package xatu191228_V2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/30
 * @Time: 18:01
 */
public class SimpleHTTPServer {
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 注册好多的用户
        registerUsers();

        // 建立监听 Socket
        ServerSocket serverSocket = new ServerSocket(8888);
        // 建立线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        // 循环着：读取请求-发送响应
        while (true) {
            // 3. 等待客户端的连接（Chrome)
            Socket socket = serverSocket.accept();
            pool.execute(new Task(socket));
        }
    }

    private static void registerUsers() {
        users.add(new User("peixinchen", "https://marketplace.canva.cn/EADcCC1MEjA/1/0/800w/canva-%E6%A9%99%E8%93%9D%E8%89%B2%E7%85%A7%E7%89%87%E4%B8%AA%E4%BA%BA%E5%88%86%E4%BA%AB%E5%BE%AE%E4%BF%A1%E5%A4%B4%E5%83%8F-Z6ykgeX5Pr4.jpg", "懒得说"));
        users.add(new User("bogao", "https://marketplace.canva.cn/EADcCC1MEjA/1/0/800w/canva-%E6%A9%99%E8%93%9D%E8%89%B2%E7%85%A7%E7%89%87%E4%B8%AA%E4%BA%BA%E5%88%86%E4%BA%AB%E5%BE%AE%E4%BF%A1%E5%A4%B4%E5%83%8F-Z6ykgeX5Pr4.jpg", "懒得说"));
        users.add(new User("gaobo", "https://marketplace.canva.cn/EADcCC1MEjA/1/0/800w/canva-%E6%A9%99%E8%93%9D%E8%89%B2%E7%85%A7%E7%89%87%E4%B8%AA%E4%BA%BA%E5%88%86%E4%BA%AB%E5%BE%AE%E4%BF%A1%E5%A4%B4%E5%83%8F-Z6ykgeX5Pr4.jpg", "懒得说"));
        users.add(new User("chenpeixin", "https://marketplace.canva.cn/EADcCC1MEjA/1/0/800w/canva-%E6%A9%99%E8%93%9D%E8%89%B2%E7%85%A7%E7%89%87%E4%B8%AA%E4%BA%BA%E5%88%86%E4%BA%AB%E5%BE%AE%E4%BF%A1%E5%A4%B4%E5%83%8F-Z6ykgeX5Pr4.jpg", "懒得说"));
    }
}
