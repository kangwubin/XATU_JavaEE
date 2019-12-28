package xatu191228;

import java.io.*;
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
            // 处理业务
            if (request.path.equals("/hello")) {
                String body = "<h1>你好</h1>";
                byte[] bodyBytes = body.getBytes("UTF-8");

                // 拼接响应并发送
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Content-Type: text/plain;charset=UTF-8\r\n");
                responseSB.append("Content-Length: ");
                responseSB.append(bodyBytes.length);
                responseSB.append("\r\n");
                responseSB.append("\r\n");
                responseSB.append(body);
                // 发送响应
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
                responseSB.append("Location: https://www.baidu.com/\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/set-cookie")) {
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Set-Cookie: name=peixinchen; expires=Mon, 06-Apr-2020 03:41:01 GMT; Max-Age=8640000\r\n");
                responseSB.append("Set-Cookie: age=34; expires=Mon, 06-Apr-2020 03:41:01 GMT; Max-Age=8640000\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.contains("/login")) {
                // 获取当前登陆的用户名
                String[] group = request.path.split("=");
                if (group.length != 2) {
                    StringBuilder responseSB = new StringBuilder();
                    responseSB.append("HTTP/1.0 401 Unauthorized\r\n");
                    responseSB.append("\r\n");
                    // 发送响应
                    os.write(responseSB.toString().getBytes("UTF-8"));
                    os.flush();
                } else {
                    String username = request.path.split("=")[1];
                    // 判断是否是登陆用户
                    User currentUser = null;
                    for (User user : SimpleHTTPServer.users) {
                        if (user.username.equals(username)) {
                            currentUser = user;
                        }
                    }
                    if (currentUser == null) {
                        StringBuilder responseSB = new StringBuilder();
                        responseSB.append("HTTP/1.0 401 Unauthorized\r\n");
                        responseSB.append("\r\n");
                        // 发送响应
                        os.write(responseSB.toString().getBytes("UTF-8"));
                        os.flush();
                    } else {

                        // 保存 session
                        String filename = String.format("sessions/%s.session", currentUser.id);
                        OutputStream fileOS = new FileOutputStream(filename);
                        ObjectOutputStream oos = new ObjectOutputStream(fileOS);
                        oos.writeObject(currentUser);
                        fileOS.close();

                        // 返回 response 登陆成功，并且种 cookie
                        String body = "<h1>恭喜，登陆成功</h1>";
                        StringBuilder responseSB = new StringBuilder();
                        responseSB.append("HTTP/1.0 200 OK\r\n");
                        responseSB.append("Content-Type: text/html;charset=UTF-8\r\n");
                        responseSB.append("Content-Length: ");
                        responseSB.append(body.getBytes("UTF-8").length);
                        responseSB.append("\r\n");
                        responseSB.append("Set-Cookie: session_id=");
                        responseSB.append(currentUser.id);
                        responseSB.append("\r\n");
                        responseSB.append("\r\n");
                        responseSB.append(body);
                        // 发送响应
                        os.write(responseSB.toString().getBytes("UTF-8"));
                        os.flush();
                    }
                }
            } else if (request.path.equals("/profile")) {
                // 获取 cookie 中的 sessionId
                String cookie = request.headers.get("Cookie");
                if (cookie == null) {
                    StringBuilder responseSB = new StringBuilder();
                    responseSB.append("HTTP/1.0 401 Unauthorized\r\n");
                    responseSB.append("\r\n");
                    // 发送响应
                    os.write(responseSB.toString().getBytes("UTF-8"));
                    os.flush();
                } else {
                    String sessionId = cookie.split("=")[1];

                    // 根据 session-id 获取用户信息
                    String filename = String.format("sessions/%s.session", sessionId);
                    File file = new File(filename);
                    if (file.exists()) {
                        System.out.println(filename);
                        InputStream fileOS = new FileInputStream(filename);
                        ObjectInputStream ois = new ObjectInputStream(fileOS);
                        User currentUser = (User) ois.readObject();
                        fileOS.close();

                        // 返回 response 个人信息 并且种 cookie
                        String body = String.format("<h1>%s</h1><img height='200' width='200' src='%s'><p>%s</p>",
                                currentUser.username,
                                currentUser.avatarUrl,
                                currentUser.show
                        );

                        StringBuilder responseSB = new StringBuilder();
                        responseSB.append("HTTP/1.0 200 OK\r\n");
                        responseSB.append("Content-Type: text/html;charset=UTF-8\r\n");
                        responseSB.append("Content-Length: ");
                        responseSB.append(body.getBytes("UTF-8").length);
                        responseSB.append("\r\n");
                        responseSB.append("Set-Cookie: session_id=");
                        responseSB.append(currentUser.id);
                        responseSB.append("\r\n");
                        responseSB.append("\r\n");
                        responseSB.append(body);
                        // 发送响应
                        os.write(responseSB.toString().getBytes("UTF-8"));
                        os.flush();
                    } else {
                        StringBuilder responseSB = new StringBuilder();
                        responseSB.append("HTTP/1.0 401 Unauthorized\r\n");
                        responseSB.append("\r\n");
                        // 发送响应
                        os.write(responseSB.toString().getBytes("UTF-8"));
                        os.flush();
                    }
                }
            } else {
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 404 Not Found\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            }
            // 关闭 socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
