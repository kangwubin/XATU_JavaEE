package xatu_20200108;

import java.io.IOException;
import java.net.Socket;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/10
 * @Time: 20:03
 */
public class ShowResetClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9876);
        socket.close();
    }
}
