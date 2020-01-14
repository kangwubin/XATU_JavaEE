public class NotFoundServlet extends HttpServlet {
    @Override
    public void doGet(Request req, Response resp) throws Exception {
        resp.setStatus("404 Not Found");
        resp.println("<h1>没有找到这个页面</h1>");
    }
}
