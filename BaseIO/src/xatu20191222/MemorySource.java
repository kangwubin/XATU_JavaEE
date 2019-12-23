package xatu20191222;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/22
 * @Time: 15:21
 */
public class MemorySource {
    public static void main(String[] args) throws IOException {
        {
            /*从内存中读，数据源在内存；*/
            byte[] source = "挖煤 挣钱 敲代码 更挣钱".getBytes("UTF-8");
            InputStream is = new ByteArrayInputStream(source);

            Scanner scanner = new Scanner(is);
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
            scanner.close();
        }
    }
}
