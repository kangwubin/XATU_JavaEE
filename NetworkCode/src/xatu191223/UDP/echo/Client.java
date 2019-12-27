package xatu191223.UDP.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();
        while (true) {
            System.out.print("请输入要发送的消息: ");
            String message = scanner.nextLine();

            byte[] sendBuffer = message.getBytes("UTF-8");
            InetAddress address = InetAddress.getLocalHost();
            // 192.168.3.100
           /* InetAddress address = InetAddress.getByAddress(
                    new byte[]{(byte) 192, (byte) 168, (byte) 137, 1}
            );*/
            int port = 8888;
            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer,
                    0,
                    sendBuffer.length,
                    address,
                    port
            );
            socket.send(sendPacket);
            byte[] receiveBuffer = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(
                    receiveBuffer,
                    0,
                    receiveBuffer.length
            );
            socket.receive(receivePacket);
            String echoMessage = new String(receiveBuffer, 0,
                    receivePacket.getLength(), "UTF-8");
            System.out.println(echoMessage);
        }
    }
}
