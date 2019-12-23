package xatu20191222;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/22
 * @Time: 10:53
 */
public class ListDirectoryWithQueue {
    private static class Node {
        Node root;
        int level;

        public Node(Node root, int level) {
            this.root = root;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        File file = new File("F:\\XATU_Bit_JavaEE");
    }
}
