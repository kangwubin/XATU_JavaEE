import java.io.*;
import java.util.UUID;

public class SessionServer {
    public static String put(User user) throws IOException {
        String sessionId = UUID.randomUUID().toString();
        String filename = "会话\\" + sessionId;

        OutputStream os = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(user);
        os.close();

        return sessionId;
    }

    public static User get(String sessionId) throws IOException, ClassNotFoundException {
        String filename = "会话\\" + sessionId;
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }

        InputStream is = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(is);
        User user = (User)ois.readObject();
        is.close();

        return user;
    }
}
