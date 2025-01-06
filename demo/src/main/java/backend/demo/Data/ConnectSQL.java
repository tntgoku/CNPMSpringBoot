package backend.demo.Data;

import java.sql.*;
public class ConnectSQL {
    private static final String URL = "jdbc:sqlserver://TRUNGHIEU;encrypt=true;trustServerCertificate=true;databaseName=iPhoneShop";
    private static final String USER = "sa";
    private static final String PASSWORD = "Hieulsdd123@";
    public static Connection conn;
    public static Connection getConnection() throws SQLException {
        // Kết nối đến SQL Server
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


     public static ResultSet selectQuery(String sql, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Thiết lập các tham số
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet selectQuery(String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           conn=getConnection();
            // Chuẩn bị câu lệnh SQL
            ps = conn.prepareStatement(sql);
            
            // Thực thi truy vấn và trả về kết quả
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int updateQuery(String sql, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Thiết lập các tham số
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
}

