public class JokeJSServlet extends HttpServlet {
    @Override
    public void doGet(Request req, Response resp) throws Exception {
        resp.setHeader("Content-Type", "application/javascript; charset=UTF-8");
        resp.println("alert('成功');");
    }
}
