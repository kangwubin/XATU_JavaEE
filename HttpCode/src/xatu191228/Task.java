package xatu191228;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/28
 * @Time: 9:40
 */
public class Task implements Runnable {
    private final Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            // 读取请求并解析
            Request request = Request.parse(is);
            System.out.println(request);

            //处理业务
            if (request.path.equals("/hello")) {
                String body = "<h1>你好</h1>";
                byte[] bodyBytes = body.getBytes("UTF-8");
                //拼接响应并发送
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                //HTML
                responseSB.append("Content-Type:text/html;charset=UTF-8\r\n");
                responseSB.append("Content-Length:");
                responseSB.append(bodyBytes.length);
                responseSB.append("\r\n");
                responseSB.append("\r\n");
                responseSB.append(body);
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/run")) {
                String body = "<script src='/joke.js'></script>";
                byte[] bodyBytes = body.getBytes("UTF-8");

                // 拼接响应并发送
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Content-Type: text/html;charset=UTF-8\r\n");
                responseSB.append("Content-Length: ");
                responseSB.append(bodyBytes.length);
                responseSB.append("\r\n");
                responseSB.append("\r\n");
                responseSB.append(body);
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/joke.js")) {
                String body = "alert('不给糖果就捣蛋');";
                byte[] bodyBytes = body.getBytes("UTF-8");
                // 拼接响应并发送
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Content-Type: application/javascript;charset=UTF-8\r\n");
                responseSB.append("Content-Length: ");
                responseSB.append(bodyBytes.length);
                responseSB.append("\r\n");
                responseSB.append("\r\n");
                responseSB.append(body);
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/move")) {
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 307 Temporary Redirect\r\n");
                responseSB.append("Location:https://www.baidu.com/\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else {
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 404 Not Found\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            }

            //关闭socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
