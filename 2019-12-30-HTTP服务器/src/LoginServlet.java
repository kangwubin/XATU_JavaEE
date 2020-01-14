public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(Request req, Response resp) throws Exception {
        String username = req.parameters.get("username");
        if (username == null) {
            resp.setStatus("401 Unauthorized");
            resp.println("<h1>请登录</h1>");
            return;
        }

        // 验证用户是否登录成功
        User user = User.find(username);
        if (user == null) {
            resp.setStatus("401 Unauthorized");
            resp.println("<h1>用户不存在</h1>");
            return;
        }

        // 把用户信息保存到 SessionServer 中
        String sessionId = SessionServer.put(user);

        // 把 sessionId 种到 Cookie 中
        resp.setHeader("Set-Cookie", "session-id=" + sessionId);

        resp.println("<h1>登录成功，欢迎 " + username + "</h1>");
    }
}
