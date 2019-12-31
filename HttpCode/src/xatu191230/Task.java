package xatu191230;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Task implements Runnable {
    private Socket socket;

    /**
     * key: url
     * value: 交给哪个 HttpServlet 对象去处理
     * map<String, HttpServlet>
     */
    private Map<String, HttpServlet> urlMap = new HashMap<>();
    private HttpServlet notFoundServlet = new NotFoundServlet();
    private StaticServlet staticServlet = new StaticServlet();


    public Task(Socket socket) {
        this.socket = socket;
        urlMap.put("/joke.js", new JokeJSServlet());
        urlMap.put("/login", new LoginServlet());
        urlMap.put("/profile", new ProfileServlet());
    }

    @Override
    public void run() {
        try {
            Request request = Request.parse(socket.getInputStream());
            System.out.println(request);
            Response response = new Response();

            HttpServlet servlet = urlMap.get(request.url);
            if (servlet == null) {
                if (StaticServlet.exists(request.url)) {
                    servlet = staticServlet;
                } else {
                    servlet = notFoundServlet;
                }
            }

            servlet.doGet(request, response);

            response.writeAndFlush(socket.getOutputStream());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
