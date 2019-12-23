package xatu20191222;

import java.io.File;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/22
 * @Time: 10:32
 */
public class Test1 {
    private static void listDirectory(File root, int level) {
        File[] files = root.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File file : files) {
            for (int i = 0; i < level; i++) {
                System.out.print(" ");
            }
            System.out.println(file.getName());
            if (file.isDirectory()) {
                System.out.println(File.separator);
            } else {
                double length = file.length();
                if (length > 1024 * 1024 * 1024) {
                    length = file.length() / 1024 / 1024 / 1024;
                    System.out.println(" " + length + "GB");
                } else if (length > 1024 * 1024) {
                    length = file.length() / 1024 / 1024;
                    System.out.println(" " + length + "MB");
                } else if (length > 1024) {
                    length = file.length() / 1024;
                    System.out.println(" " + length + "KB");
                } else {
                    System.out.println(" " + length + "byte");
                }
                System.out.println(file.length());
            }
            if (file.exists()) {
                listDirectory(file, level + 1);
            }
        }
    }

    public static void main(String[] args) {
        File root = new File("F:\\XATU_Bit_JavaEE");
        System.out.println(root.getAbsolutePath());
        listDirectory(root, 1);
    }
}
