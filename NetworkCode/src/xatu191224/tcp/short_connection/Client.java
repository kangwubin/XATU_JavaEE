package xatu191224.tcp.short_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Socket socket = new Socket("127.0.0.1", 8888);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            String message = scanner.nextLine();
            message = message + "\r\n";
            os.write(message.getBytes("UTF-8"));

            Scanner tcpScanner = new Scanner(is, "UTF-8");
            System.out.println(tcpScanner.nextLine());

            socket.close();
        }
    }
}
