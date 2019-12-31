package xatu191230;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StaticServlet extends HttpServlet {
    public static String docBase = "F:\\XATU_JavaEE\\HttpCode\\docBase";
    public static Map<String, String> mimeType = new HashMap<>();

    static {
        mimeType.put("js", "application/javascript; charset=UTF-8");
        mimeType.put("css", "text/css; charset=UTF-8");
    }

    // /hello.html
    // docBase/hello.html
    public static boolean exists(String url) {
        File file = new File(docBase + url);
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            return false;
        }

        return true;
    }

    // 支持文本文件
    @Override
    public void doGet(Request req, Response resp) throws Exception {
        int index = req.url.lastIndexOf('.');
        String suffix = req.url.substring(index + 1);
        String contentType = mimeType.getOrDefault(suffix, "text/html; charset=UTF-8");
        resp.setHeader("Content-Type", contentType);

        // Content-Type html
        String filename = docBase + req.url;
        InputStream is = new FileInputStream(filename);
        Scanner scanner = new Scanner(is, "UTF-8");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            resp.println(line);
        }
    }
}
