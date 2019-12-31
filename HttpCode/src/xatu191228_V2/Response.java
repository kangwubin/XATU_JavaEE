package xatu191228_V2;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/28
 * @Time: 16:39
 */
//查看进程的端口号：netstat -nao | findstr 端口号
public class Response {
    String status;
    Map<String, String> headers = new HashMap<>();
    StringBuilder bodyBuilder = new StringBuilder();

    void setStatus(String status) {
        this.status = status;
    }

    void setHeaders(String key, String value) {
        this.headers.put(key, value);
    }

    void println(String s) {
        bodyBuilder.append(s);
    }

    void writeAndFlush(OutputStream os) throws IOException {
        // 计算 Content-Length
        setHeaders("Content-Length",
                String.valueOf(bodyBuilder.toString().getBytes("UTF-8").length));
        StringBuilder responseBuilder = new StringBuilder();
        // 状态行
        responseBuilder.append("HTTP/1.0 ");
        responseBuilder.append(status);
        responseBuilder.append("\r\n");
        // 响应头
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            responseBuilder.append(entry.getKey());
            responseBuilder.append(":");
            responseBuilder.append(entry.getValue());
            responseBuilder.append("\r\n");
        }
        responseBuilder.append("\r\n");
        responseBuilder.append(bodyBuilder);
        os.write(responseBuilder.toString().getBytes("UTF-8"));
        os.flush();
    }
}
