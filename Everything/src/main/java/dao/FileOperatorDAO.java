package dao;

import app.FileMeta;
import task.FileScanCallback;
import util.DBUtil;
import util.Pinyin4Until;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/5
 * @Time: 16:15
 */
public class FileOperatorDAO implements FileScanCallback {
    @Override
    public void execute(File dir) {
        //文件比对
        if (dir.isDirectory()) {   //TODO 是否考虑文件的操作
            //本地文件
            File[] children = dir.listFiles();
            List<FileMeta> localMetas = compose(children);
            //数据库文件:查询
            List<FileMeta> metas = query(dir.getPath());
            //本地有，数据库没有的文件
            for (FileMeta localMeta : localMetas) {
                //contains--需要FileMeta实现hashCode()和equals()方法
                if (!metas.contains(localMeta)) {
                    insert(localMeta);
                }
            }
            //数据库没有，本地没有的文件
            for (FileMeta meta : metas) {
                if (!localMetas.contains(meta)) {
                    delete(meta);
                }
            }
        }
        //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            try {
                connection = DBUtil.getConnection();
                String sql = "insert into file_meta" +
                        "(name, path, is_directory, pinyin, pinyin_first, size, last_modified)" +
                        "values (?,?,?,?,?,?,?)";
                //2.获取操作命令对象
                statement = connection.prepareStatement(sql);
                //填充占位符
                statement.setString(1, dir.getName());
                statement.setString(2, dir.getParent());
                statement.setBoolean(3, dir.isDirectory());
                String pinyin = null;
                String pinyin_first = null;
                //包含中文字符时，需要保存全拼和拼音首字符
                if (Pinyin4Until.containsChinese(dir.getName())) {
                    String[] pinyins = Pinyin4Until.get(dir.getName());
                    pinyin = pinyins[0];
                    pinyin_first = pinyins[1];
                }
                statement.setString(4, pinyin);
                statement.setString(5, pinyin_first);
                statement.setLong(6, dir.length());
                statement.setTimestamp(7, new Timestamp(dir.lastModified()));
                //3.执行sql语句
                statement.executeUpdate();
            } finally {
                DBUtil.close(connection, statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
