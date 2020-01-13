package dao;

import app.FileMeta;
import util.DBUtil;
import util.Pinyin4Until;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/12
 * @Time: 9:21
 */
public class FileOperatorDAO {
    /*插入*/
    public static void insert(FileMeta localMeta) {
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
                statement.setString(1, localMeta.getName());
                statement.setString(2, localMeta.getPath());
                statement.setBoolean(3, localMeta.getDirectory());
                String pinyin = null;
                String pinyin_first = null;
                System.out.println("insert:" + localMeta.getName());
                //包含中文字符时，需要保存全拼和拼音首字符
                if (Pinyin4Until.containsChinese(localMeta.getName())) {
                    String[] pinyins = Pinyin4Until.get(localMeta.getName());
                    pinyin = pinyins[0];
                    pinyin_first = pinyins[1];
                }
                statement.setString(4, pinyin);
                statement.setString(5, pinyin_first);
                statement.setLong(6, localMeta.getSize());
                statement.setTimestamp(7, new Timestamp(localMeta.getLastModified()));
                //3.执行sql语句
                System.out.println("insert:" + localMeta.getName() + localMeta.getPath());
                statement.executeUpdate();
            } finally {
                DBUtil.close(connection, statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*查询操作*/
    public static List<FileMeta> query(String dirpath) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<FileMeta> metas = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select name,path,size,last_modified, is_directory " +
                    "from file_meta where path=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1, dirpath);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                boolean is_directory = resultSet.getBoolean("is_directory");
                long size = resultSet.getLong("size");
                long last_modified = resultSet.getLong("last_modified");
//                System.out.println(name);
                FileMeta meta = new FileMeta(name, path, size, last_modified, is_directory);
                System.out.println("query:" + meta.getName() + meta.getPath());
                metas.add(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return metas;
    }

    /*删除*/
    public static void delete(FileMeta meta) {
        //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            //在JDBC里操作事务
            connection.setAutoCommit(false);
            String sql = "delete from file_meta where name=?" +
                    "and path=? and is_directory=?";
            //2.获取操作命令对象
            statement = connection.prepareStatement(sql);
            //填充占位符
            statement.setString(1, meta.getName());
            statement.setString(2, meta.getPath());
            statement.setBoolean(3, meta.getDirectory());
            String pinyin = null;
            //3.执行sql语句
            statement.executeUpdate();
            //删除子文件夹
            if (meta.getDirectory()) {
                sql = "delete from file_meta where path=? or path like ?";
                statement = connection.prepareStatement(sql);
                String path = meta.getPath() + File.separator + meta.getName();
                statement.setString(1, path);
                statement.setString(2, path + File.separator + "%");
                /*statement.setString(1, meta.getPath() + File.separator +
                        meta.getName() + File.separator + "%");*/
                System.out.println("delete like:" + meta.getPath() + File.separator + meta.getName());
                statement.executeUpdate();
            }
            System.out.println("delete:" + meta.getName() + meta.getPath());
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            DBUtil.close(connection, statement);
        }
    }

    /* path=""
     * */
    public static List<FileMeta> search(String dir, String text) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<FileMeta> metas = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            boolean empty = dir == null || dir.trim().length() == 0;
            String sql = "select name,path,size,last_modified, is_directory" +
                    " from file_meta where name like ? or  pinyin like ? " +
                    " or pinyin_first like ? "
                    + (empty ?
                    "" : "and (path=? or path like ?)");
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + text + "%");
            statement.setString(2, "%" + text + "%");
            statement.setString(3, "%" + text + "%");
            if (!empty) {
                statement.setString(4, dir);
                statement.setString(5, dir + File.separator + "%");
            }
//            statement.setString(2, text + File.separator + "%");
            System.out.println("search path=" + dir + "," + "text=" + text);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                boolean is_directory = resultSet.getBoolean("is_directory");
                long size = resultSet.getLong("size");
                long last_modified = resultSet.getLong("last_modified");
//                System.out.println(name);
                FileMeta meta = new FileMeta(name, path, size, last_modified, is_directory);
                System.out.println("query:" + meta.getName() + meta.getPath());
                metas.add(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return metas;
    }

    //测试
    public static void main(String[] args) {
//        System.out.println(query("F:\\【比特科技书籍推荐】Java研发方向"));
//        delete(new FileMeta("【比特科技推荐】Head First Java 中文版.pdf", "F:\\", 4528245L, 0L, false));
    }
}
