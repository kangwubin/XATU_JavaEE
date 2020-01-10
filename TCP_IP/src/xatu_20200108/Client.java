package xatu_20200108;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/10
 * @Time: 19:30
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1", 9876);
        System.out.println("ESTABLISHED");
        scanner.nextLine();
        System.out.println("关闭连接，发送 FIN");
        socket.close();
    }
}
