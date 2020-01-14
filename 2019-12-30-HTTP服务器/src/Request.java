import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// /login?username=peixinchen&age=18
public class Request {
    String method;
    // /login
    String url;
    String version;
    Map<String, String> headers = new HashMap<>();
    // { username = peixinchen, age = 18 }
    Map<String, String> parameters = new HashMap<>();

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", headers=" + headers +
                ", parameters=" + parameters +
                '}';
    }

    public static Request parse(InputStream is) throws IOException {
        Request request = new Request();
        Scanner scanner = new Scanner(is, "UTF-8");
        parseRequestLine(request, scanner.nextLine());
        parseRequestHeaders(request, scanner);

        return request;
    }

    private static void parseRequestHeaders(Request request, Scanner scanner) {
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            String[] kv = line.split(":");
            String key = kv[0].trim();
            String value = kv[1].trim();
            request.headers.put(key, value);
        }
    }

    private static void parseRequestLine(Request request, String requestLine) throws IOException {
        String[] group = requestLine.split(" ");
        request.method = group[0];
        parseUrlAndQueryString(request, group[1]);
        request.version = group[2];
    }

    // /login?username=peixinchen&age=18
    private static void parseUrlAndQueryString(Request request, String s) throws IOException {
        // 正则表达式 \?
        String[] group = s.split("\\?");
        request.url = URLDecoder.decode(group[0], "UTF-8");
        if (group.length == 2) {
            String[] kvGroup = group[1].split("&");
            for (String kvString : kvGroup) {
                String[] kv = kvString.split("=");
                String key = URLDecoder.decode(kv[0], "UTF-8");
                String value = URLDecoder.decode(kv[1], "UTF-8");
                request.parameters.put(key, value);
            }
        }
    }
}
