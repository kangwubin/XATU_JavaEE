package xatu20191222;

import java.io.*;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/22
 * @Time: 11:28
 */
public class InputDemo {

    private static String 从一个输入字节流中得到最终的字符(InputStream is) throws IOException {
        byte[] buffer = new byte[8192];
     /*   int len = 8192;
        for (int i = 0; i < buffer.length; i++) {
            int b = is.read();
            if (b == -1) {
                len = i;
                break;
            }
            buffer[i] = (byte) b;
        }*/
        int len = is.read(buffer);
        String s = new String(buffer, 0, len, "UTF-8");
        return s;
    }

    private static InputStream 获取一个输入字节流() throws FileNotFoundException {
        InputStream is;
        is = new FileInputStream("本地文件.txt");
        return is;
    }

    public static void main(String[] args) throws FileNotFoundException {

    }
}
