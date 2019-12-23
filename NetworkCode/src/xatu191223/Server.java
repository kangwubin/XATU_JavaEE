package xatu191223;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/23
 * @Time: 20:05
 */
public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(8888);
        while (true) {
            byte[] buffer = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(receivePacket);

            int len = receivePacket.getLength();
            String message = new String(buffer, 0, len, "UTF-8");

            String echoMessage = message;
            byte[] sendBuffer = echoMessage.getBytes("UTF-8");
            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer, 0, sendBuffer.length,
                    receivePacket.getAddress(), receivePacket.getPort()
            );
            socket.send(sendPacket);
        }
    }
}
