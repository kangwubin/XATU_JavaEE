package xatu20191222;

import java.io.*;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/22
 * @Time: 15:31
 */
public class OutPutInFile {
    /*输出到文件中*/
    public static void main(String[] args) throws IOException {
        {
            OutputStream os = new FileOutputStream("本地输出文件.txt");
            os.write(0x41);
            os.write(0x0d);
            os.write(0x0a);
            os.flush();
            os.close();
        }

        {
            OutputStream os = new FileOutputStream("本地输出文件1.txt");
            Writer writer = new OutputStreamWriter(os, "UTF-8");
            writer.write('c');
            writer.write("\r\n中国人\r\n");
            char[] buffer = {'我', '要', '写', '代', '码', '挣', '钱'};
            writer.write(buffer, 0, buffer.length);
            writer.append("\r\n我正在改变世界");
            writer.flush();
            writer.close();
        }

        {
           /* OutputStream os = new FileOutputStream("本地输出文件2.txt");
            PrintStream out = new PrintStream(os, false, "UTF-8");*/
            PrintStream out = new PrintStream("本地输出文件2.txt", "UTF-8");
            out.println("我是中国人");
            out.println("我在第二行");
            out.println("为中华崛起而读书");
            out.flush();
            out.close();
        }

        {
            /* %n == /r/n */
        }
    }
}
