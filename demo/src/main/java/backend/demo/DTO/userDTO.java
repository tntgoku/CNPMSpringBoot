package backend.demo.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import backend.demo.Model.uSer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class userDTO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public uSer getUser(String emails,String pwd){
        String sql="SELECT * FROM Users where Email = ? and PasswordHash = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{emails,pwd}, this::mapRowToCustomer);
    } 
    public boolean checkUser(String emails,String pwd){
        String sql="SELECT COUNT(*) FROM Users where Email = ? and PasswordHash = ?";
        int count=0;
        count=jdbcTemplate.queryForObject(sql, new Object[]{emails,pwd},Integer.class);
        if(count!=0){
            return false;
        }
        return true;
    }

    public boolean isAdmin(int role){
        if(role==1){
            return true;
        }
        return false;
    }
    private uSer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        return new uSer(
            rs.getInt(1),           // userID
            rs.getString(2),        // fullName
            rs.getString(3),        // email
            rs.getString(4),        // passwordHash
            rs.getString(5),        // phone
            rs.getString(6),        // address
            rs.getInt(7),           // roles
            rs.getString(8)         // timer
        );
    }
      // Thêm khách hàng mới vào bảng Users
      public int addUser(String fullName, String email, String passwordHash, String phone, String address, int roles) {
        String sql = "INSERT INTO Users (FullName, Email, PasswordHash, Phone, Address, Roles, Timer) " +
                     "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
        return jdbcTemplate.update(sql, fullName, email, passwordHash, phone, address, roles);
    }

    public boolean isEmailExist(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;  // Nếu email tồn tại, trả về true
    }
    
    // Phương thức cập nhật thông tin người dùng
    public int updateUser(uSer user) {
        String sql = "UPDATE Users SET FullName = ?, Email = ?, PasswordHash = ?, Phone = ?, Address = ?, Roles = ?, Timer = GETDATE() " +
                    "WHERE userID = ?";

        // Thực hiện cập nhật người dùng theo id
        return jdbcTemplate.update(sql, 
                user.getFullName(), 
                user.getEmail(), 
                user.getPasswordHash(), 
                user.getPhone(), 
                user.getAddress(), 
                user.getRoles(), 
                user.getUserID());
    }


    public int DeleteUser(uSer user){
        String sql = "DELETE FROM Users " +
        "WHERE userID = ?";
        return jdbcTemplate.update(sql,user.getUserID());
    }

}
