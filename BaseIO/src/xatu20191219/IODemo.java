package xatu20191219;

import java.io.File;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/19
 * @Time: 11:14
 */
public class IODemo {
    public static void main(String[] args) {
        File parent = new File("F:\\XATU_JavaEE\\BaseIO\\测试目录"
                + File.separator + "Hello.java");
        /*属性相关的*/
        System.out.println("可执行/可读/可写");
        System.out.println(parent.canExecute());
        System.out.println(parent.canRead());
        System.out.println(parent.canWrite());

        /*绝对路径：*/
        System.out.println("是否存在/是文件夹/是文件/绝对路径/隐藏");
        System.out.println(parent.exists());
        System.out.println(parent.isDirectory());
        System.out.println(parent.isFile());
        System.out.println(parent.isAbsolute());
        System.out.println(parent.isHidden());

        System.out.println("该文件所在盘符的空间情况");
        System.out.println(parent.getTotalSpace());
        System.out.println(parent.getFreeSpace());
        System.out.println(parent.getUsableSpace());

        System.out.println("文件大小/文件名/上次修改时间/");
        System.out.println(parent.length());
        System.out.println(parent.getName());
        System.out.println(parent.lastModified());
    }
}
