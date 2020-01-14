public class ProfileServlet extends HttpServlet {
    @Override
    public void doGet(Request req, Response resp) throws Exception {
        String cookie = req.headers.get("Cookie");
        if (cookie == null) {
            resp.setStatus("401 Unauthorized");
            resp.println("<h1>请登录后查看</h1>");
            return;
        }

        String sessionId = cookie.split("=")[1];
        User user = SessionServer.get(sessionId);
        if (user == null) {
            resp.setStatus("401 Unauthorized");
            resp.println("<h1>请登录后查看</h1>");
            return;
        }

        resp.println("<h1>用户名: " + user.username + "</h1>");
        resp.println("<h1>账户余额: " + user.账户余额 + "</h1>");
    }
}
