package xatu191228_V2;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/28
 * @Time: 16:38
 */
public class Request {
    String method;
    String path;
    String version;
    Map<String, String> headers = new HashMap<>();

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", version='" + version + '\'' +
                ", headers=" + headers +
                '}';
    }

    public static Request parse(InputStream is) {
        Request request = new Request();
        // UTF-8 是假设的，实际上应该是通过 header 去判断编码
        Scanner scanner = new Scanner(is, "UTF-8");
        String line;
        line = scanner.nextLine();
        String[] group = line.split(" ");
        request.method = group[0];
        request.path = group[1];
        request.version = group[2];
        while (!(line = scanner.nextLine()).isEmpty()) {
            String[] kv = line.split(":");
            String key = kv[0].trim();
            String value = kv[1].trim();
            request.headers.put(key, value);
        }
        return request;
    }
}
