package xatu191223.UDP.calculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
    private static String parse(String message) {
        String[] items = message.split(" ");
        int a = Integer.parseInt(items[0]);
        String operator = items[1];
        int b = Integer.parseInt(items[2]);

        int r;
        switch (operator) {
            case "+": r = a + b; break;
            case "-": r = a - b; break;
            case "*": r = a * b; break;
            default:
                r = -1;
        }

        return String.valueOf(r);
    }

    public static void main(String[] args) throws IOException {
        // 创建服务器 socket，同时还绑定了 8888 端口，默认绑定的 ip 是所有 ip
        DatagramSocket socket = new DatagramSocket(8888);

        while (true) {
            byte[] buffer = new byte[4096]; // 准备了一个接收缓冲区
            // 创建了一个接收报文
            DatagramPacket receivePacket = new DatagramPacket(buffer, 0, buffer.length);
            // 当 receive 返回时，OS 就会把对方发送过来的数据填充到接收缓冲区中
            socket.receive(receivePacket);

            // 获取实际收到的数据的长度
            int len = receivePacket.getLength();
            // 把字节转化字符
            String message = new String(buffer, 0, len, "UTF-8");
            System.out.println("收到了消息：" + message);

            // 准备回给对方的消息，回声
            String echoMessage = parse(message);
            // 把字符转字节
            byte[] sendBuffer = echoMessage.getBytes("UTF-8");
            // 创建发送报文，带有 1. 发送缓冲区 2. 对方的 ip 3. 对方的 port
            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer,
                    0,
                    sendBuffer.length,
                    receivePacket.getAddress(),
                    receivePacket.getPort()
            );
            // 发送，发送成功只是代表数据发送到网络上了，不代表对方收到了
            socket.send(sendPacket);
        }
        //socket.close();
    }
}
