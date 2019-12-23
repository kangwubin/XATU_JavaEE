package xatu191223;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/23
 * @Time: 20:16
 */
public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        String message = "写代码，挣钱；程序员，改变世界!";
        byte[] sendBuffer = message.getBytes("UTF-8");
        InetAddress address = InetAddress.getLocalHost();
        //192.168.3.100
//        InetAddress address = InetAddress.getByAddress(new byte[]{(byte) 192, (byte) 168, (byte) 137, 1});
        int port = 8888;
        DatagramPacket sendPacket = new DatagramPacket(
                sendBuffer, 0, sendBuffer.length, address, port);
        socket.send(sendPacket);
        byte[] receiveBuffer = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(
                receiveBuffer, 0, receiveBuffer.length);
        socket.receive(receivePacket);
        String echoMessage = new String(
                receiveBuffer, 0,
                receivePacket.getLength(), "utf-8");
        System.out.println(echoMessage);
        socket.close();
    }
}
