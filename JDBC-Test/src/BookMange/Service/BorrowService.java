package BookMange.Service;

import BookMange.DBUtil.DBUtil;
import BookMange.model.BorrowInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:JDBC数据库连接代码的复用设计与多表联合查询
 *
 * @author: KangWuBin
 * @Date: 2019/11/30
 * @Time: 17:00
 */
public class BorrowService {
    // 通过BookName进行查询
    public static List<BorrowInfo> queryByBookName(String name) {
        //获取数据库连接
        Connection connection = null;
        //数据库预编译处理语句
        PreparedStatement ps = null;
        //处理数据结果集
        ResultSet rs = null;
        List<BorrowInfo> infos = new ArrayList<>();
        try {
            //连接数据库
            connection = DBUtil.getConnection();
            //创建sql语句
            String sql = "select" +
                    "  stu.id student_id," +
                    "  stu.name student_name," +
                    "  b.id book_id," +
                    "  b.name book_name," +
                    "  bi.start_time," +
                    "  bi.end_time" +
                    "  from borrow_info bi" +
                    "  join student stu on bi.student_id = stu.id" +
                    "  join book b on bi.book_id = b.id where b.name = ?";
            //预编译sql语句
            ps = connection.prepareStatement(sql);
            //在方法里面传入的是name
//            ps.setString(1,  "西游记");
            ps.setString(1, name);
            System.out.println(ps);
            //执行sql语句
            rs = ps.executeQuery();
            //处理结果集
            while (rs.next()) {
                BorrowInfo info = new BorrowInfo();
                //rs.get()方法得到的查询的字段的名称
                info.setStudentId(rs.getInt("student_id"));
                info.setStudentName(rs.getString("student_name"));
                info.setBookId(rs.getInt("book_id"));
                info.setBookName(rs.getString("book_name"));
                info.setStartTime(rs.getTimestamp("start_time"));
                info.setEndTime(rs.getTimestamp("end_time"));
                infos.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //调用数据库的关闭数据库方法，关闭数据库
            DBUtil.close(connection, ps, rs);
        }
        return infos;
    }

    public static void main(String[] args) {
        System.out.println(queryByBookName("史记"));
    }
}
