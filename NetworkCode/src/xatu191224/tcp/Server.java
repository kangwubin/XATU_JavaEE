package xatu191224.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/24
 * @Time: 9:28
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 监听 socket
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            // 双方的通信 socket
            System.out.println("等待client连接：");
            Socket socket = serverSocket.accept();
            System.out.println("有 client 连接上来");
            /*1.获取输入流与输出流；*/
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            //封装往出发的消息
            PrintWriter printWriter = new PrintWriter(
                    new OutputStreamWriter(os, "utf-8"), false);
            Scanner scanner = new Scanner(is, "UTF-8");
            while (scanner.hasNext()) {
                String message = scanner.nextLine();
                System.out.println("收到对方的消息：" + message);
                String responseMessage = message;
                System.out.println("发送给对方的消息：" + responseMessage);
                printWriter.println(responseMessage);
                printWriter.flush();
            }
        }
    }
}
