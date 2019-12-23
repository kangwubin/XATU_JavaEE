package xatu20191222;

import java.io.*;
import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/22
 * @Time: 14:35
 */
public class InputDemo2 {
    public static void main(String[] args) throws IOException {
       /* {
            InputStream is = new FileInputStream("F:\\XATU_JavaEE\\BaseIO\\src\\本地文件1");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
            }
        }*/

        /*Reader*/
        {
            InputStream is = new FileInputStream("F:\\XATU_JavaEE\\BaseIO\\src\\本地文件");
            Reader reader = new InputStreamReader(is, "UTF-8");
            StringBuilder sb1 = new StringBuilder();
            char[] buffer = new char[1024];
            int len;
            while ((len = reader.read(buffer)) != -1) {
                sb1.append(buffer, 0, len);
            }
            System.out.println(sb1.toString());
            reader.close();
        }
        System.out.println();

        {
            InputStream is = new FileInputStream("F:\\XATU_JavaEE\\BaseIO\\src\\本地文件1");
            Reader reader = new InputStreamReader(is, "UTF-8");
            StringBuilder sb2 = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                char cb = (char) c;
                sb2.append(cb);
            }
            System.out.println(sb2.toString());
            reader.close();
        }
        System.out.println();

        {
            InputStream is = new FileInputStream("F:\\XATU_JavaEE\\BaseIO\\src\\本地文件1");
            Reader reader = new InputStreamReader(is, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                /*\r---回到行首；
                 * windows下，\r\n--是换行符；*/
                sb.append("\r\n");
            }
            System.out.println(sb.toString());
            bufferedReader.close();
        }
        System.out.println();

        {
            InputStream is = new FileInputStream("F:\\XATU_JavaEE\\BaseIO\\src\\本地文件1");
            Scanner scanner1 = new Scanner(is, "UTF-8");

            StringBuilder sb = new StringBuilder();
            while (scanner1.hasNext()) {
                String next = scanner1.next();
                sb.append(next);
            }
            System.out.println(sb.toString());
        }
        System.out.println();

        {
            InputStream is = new FileInputStream("F:\\XATU_JavaEE\\BaseIO\\src\\本地文件1");
            Reader reader = new InputStreamReader(is, "UTF-8");
            Scanner scanner2 = new Scanner(reader);
            StringBuilder sb = new StringBuilder();
            while (scanner2.hasNext()) {
                String next = scanner2.next();
                sb.append(next);
            }
            System.out.println(sb.toString());
        }
    }
}
