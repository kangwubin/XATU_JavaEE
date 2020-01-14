import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response {
    String status = "200 OK";
    Map<String, String> headers = new HashMap<>();
    StringBuilder bodyBuilder = new StringBuilder();

    Response() {
        headers.put("Content-Type", "text/html; charset=UTF-8");
    }

    public void writeAndFlush(OutputStream os) throws IOException {
        String response = buildResponse();
        System.out.println(response);
        os.write(response.getBytes("UTF-8"));
        os.flush();
    }

    private String buildResponse() throws IOException {
        StringBuilder responseBuilder = new StringBuilder();
        // 响应行
        responseBuilder.append("HTTP/1.0 ");
        responseBuilder.append(status);
        responseBuilder.append("\r\n");

        // 响应头
        int contentLength = bodyBuilder.toString().getBytes("UTF-8").length;
        headers.put("Content-Length", String.valueOf(contentLength));

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            responseBuilder.append(entry.getKey());
            responseBuilder.append(": ");
            responseBuilder.append(entry.getValue());
            responseBuilder.append("\r\n");
        }

        // 空行
        responseBuilder.append("\r\n");

        // 正文
        responseBuilder.append(bodyBuilder);

        return responseBuilder.toString();
    }

    public void setStatus(String s) {
        status = s;
    }

    public void println(String s) {
        bodyBuilder.append(s);
        bodyBuilder.append("\r\n");
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }
}
