package AAA8_3.inclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Class1 {
    public  static  String url = "jdbc:mysql://127.0.0.1:3306/schooldb?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
    //用户名
    public  static  String user = "root";
    //密码
    public  static  String pwd = "root";
    //驱动类的名称
    public static  String diverName = "com.mysql.cj.jdbc.Driver";
    public static void main(String[] args) {
        test1();
    }
    public  static void test1(){
        Connection con = null;
        try {
            //加载驱动类
            Class.forName(diverName);
            //使用DriverManager建立连接
            con = DriverManager.getConnection(url,user,pwd);
            System.out.println(con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
