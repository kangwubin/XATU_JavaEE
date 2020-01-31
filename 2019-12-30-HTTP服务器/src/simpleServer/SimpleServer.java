package simpleServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/14
 * @Time: 20:08
 */
public class SimpleServer {
    //简易版的http server V1 + 多线程 + 手动构建response 展示302 307 404效果
    /**
     * 端口号
     */
    private static final int PORT = 9999;
    /**
     * 统一编码
     */
    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        ExecutorService POOL = Executors.newCachedThreadPool();
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                POOL.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                            // 解析Http请求行
                            String httpLine = reader.readLine();
                            System.out.println("===" + httpLine);
                            String[] httpLineArray = httpLine.split(" ");
                            String requestMethod = httpLineArray[0];
                            String requestUri = httpLineArray[1];
                            String requestVersion = httpLineArray[2];
                            // 解析请求头
                            String requestHeader;
                            Map<String, String> headers = new HashMap<>();
                            while ((requestHeader = reader.readLine()) != null &&
                                    requestHeader.length() != 0) {
                                String[] headerArray = requestHeader.split(":");
                                headers.put(headerArray[0].trim(), headerArray[1].trim());
                            }
                            PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                                    socket.getOutputStream(), CHARSET), true);
                            String content;
                            if ("/307".equals(requestUri)) {
                                writer.println("HTTP/1.1 307 Temporary Redirect");
                                writer.println("Location: http://45.40.254.164");
                                content = "";
                            } else if ("/404".equals(requestUri)) {
                                writer.println("HTTP/1.1 404 Not Found");
                                content = "<h1>没有找到资源</h1>";
                            } else {
                                writer.println("HTTP/1.1 200 OK");
                                content = "<h1>My Http Server</h1>";
                            }
                            writer.println("Content-Type: text/html; charset=utf-8");
                            writer.println("Content-Length: " + content.getBytes(CHARSET).length);
                            writer.println();
                            writer.println(content.toString());
                            socket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
