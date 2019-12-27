package xatu191224.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/24
 * @Time: 10:01
 */
public class Client {
    public static void main(String[] args) throws IOException {
//        String message = "cat\r\ndog\r\nfish\r\n";
        Socket socket = new Socket("127.0.0.1", 8888);
        /*
         * Socket socket = new Socket();
         * socket.bind(本地IP + 本地port);
         * socket.connect(目标IP + 目标port);*/
        /*1.获取输入与输出流；*/
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        Scanner 读本地用户准备发送的scanner = new Scanner(System.in);
        Scanner 读server发送的Scanner = new Scanner(is, "UTF-8");
        while (true) {
            System.out.println("请提交命令：");
            String message = 读本地用户准备发送的scanner.nextLine();
            byte[] sendBuffer = message.getBytes("utf-8");
            os.write(sendBuffer);
            os.write('\r');
            os.write('\n');
            System.out.println(读server发送的Scanner.nextLine());
            /*System.out.println(读server发送的Scanner.nextLine());
            System.out.println(读server发送的Scanner.nextLine());*/
        }
//        socket.close();
    }
}
