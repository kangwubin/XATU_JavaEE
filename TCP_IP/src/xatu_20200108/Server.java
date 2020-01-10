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
 * @Time: 19:26
 */
public class Server {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(9876);
        System.out.println("LISTENING");
        scanner.nextLine();

        Socket socket = serverSocket.accept();
        System.out.println("ESTABLISHED");
        System.out.println("等待客户端先调用 close");
        System.out.println("CLOSE_WAIT");
        scanner.nextLine();
        socket.close();//关闭连接
        System.out.println("正真正关闭");
        /*socket.shutdownOutput();//只关闭写端
        socket.shutdownInput();//只关闭读端*/
    }
}
