package task;

import app.FileMeta;
import dao.FileOperatorDAO;
import task.FileScanCallback;
import util.DBUtil;
import util.Pinyin4Until;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/5
 * @Time: 16:15
 */
public class FileOperatorTask implements FileScanCallback {
    @Override
    public void execute(File dir) {
        //文件比对
        if (dir.isDirectory()) {   //TODO 是否考虑文件的操作
            //本地文件
            File[] children = dir.listFiles();
            List<FileMeta> localMetas = compose(children);
            //数据库文件:查询
            List<FileMeta> metas = FileOperatorDAO.query(dir.getPath());

            //数据库没有，本地没有的文件
            for (FileMeta meta : metas) {
                if (!localMetas.contains(meta)) {
                    FileOperatorDAO.delete(meta);
                }
            }

            //本地有，数据库没有的文件
            for (FileMeta localMeta : localMetas) {
                //自定义类型--contains--需要FileMeta实现hashCode()和equals()方法
                if (!metas.contains(localMeta)) {
                    FileOperatorDAO.insert(localMeta);
                }
            }
        }
    }

    /*FileMeta数组转FileMetaList;*/
    private List<FileMeta> compose(File[] children) {
        List<FileMeta> metas = new ArrayList<>();
        if (children != null) {
            for (File child : children) {
               /* metas.add(new FileMeta(child.getName(), child.getPath(), child.length(),
                        child.lastModified(), child.isDirectory());*/
                metas.add(new FileMeta(child));
            }
        }
        return metas;
    }
}
