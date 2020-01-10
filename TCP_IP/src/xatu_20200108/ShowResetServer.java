package xatu_20200108;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/10
 * @Time: 19:59
 */
public class ShowResetServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9876);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接上来");
            scanner.nextLine();
            socket.close();
            System.out.println("尝试一个已经关闭的socket中写数据");
            socket.getOutputStream().write("会遇到Reset".getBytes("UTF-8"));
        }
    }
}
