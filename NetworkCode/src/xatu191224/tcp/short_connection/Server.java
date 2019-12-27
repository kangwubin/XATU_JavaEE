package xatu191224.tcp.short_connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            System.out.println("等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("连接建立");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, false, "UTF-8");

            Scanner scanner = new Scanner(is, "UTF-8");
            System.out.println("等待对方输入");
            String message = scanner.nextLine();
            System.out.println("接收了对方的输入：" + message);
            String echoMessage = message;
            socket.shutdownInput();

            out.println(echoMessage);
            out.flush();

            socket.close();
        }
    }
}
