package backend.demo.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import backend.demo.Model.iPhone;
import org.springframework.stereotype.Component;
@Component
public class productDTO {
        @Autowired
    private JdbcTemplate jdbcTemplate;

	
	public productDTO(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

	public List<iPhone> GetallPhone(){
		String sql = "SELECT * FROM Products";
		try {
			return jdbcTemplate.query(sql, new RowMapper<iPhone>() {
				@Override
				public iPhone mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new iPhone(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryID"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Storage"),
                        rs.getString("Color"),
                        rs.getString("Image"),
                        rs.getInt("IsNew"),
                        rs.getString("Description"),
                        rs.getString("timer"),
						rs.getInt("discount")
                    );
				}
			});
			
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	
	public List<iPhone> GetallPhone(String i,String i1){
		String sql = "SELECT * FROM Products WHERE ProductName like ?  and "
		+"Storage like ?";
		try {
			return jdbcTemplate.query(sql, new Object[]{i.trim(),i1.trim()},
			new RowMapper<iPhone>() {
				@Override
				public iPhone mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new iPhone(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryID"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Storage"),
                        rs.getString("Color"),
                        rs.getString("Image"),
                        rs.getInt("IsNew"),
                        rs.getString("Description"),
                        rs.getString("timer"),
						rs.getInt("discount")
                    );
				}
			});
			
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	//Search
	public List<iPhone> searchPhones(String namePhone){
		String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";
		try {
			return jdbcTemplate.query(sql,new Object[]{namePhone.trim()+"%"}, new RowMapper<iPhone>() {
				@Override
				public iPhone mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new iPhone(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryID"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Storage"),
                        rs.getString("Color"),
                        rs.getString("Image"),
                        rs.getInt("IsNew"),
                        rs.getString("Description"),
                        rs.getString("timer"),
						rs.getInt("discount")
                    );
				}
			});
			
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	
	public iPhone getPhoneStorageandName(String name,String storage){
		String sql = "SELECT * FROM Products  where ProductName like ? and Storage like ? ";
		return jdbcTemplate.queryForObject(sql, new Object[]{name.trim(),storage.trim()}, this::mapRowToProduct);
	}
	public List<String> getPhoneStorage(String storage){
		String sql = "SELECT Storage, COUNT(*) AS StorageCount FROM Products " + 
		"WHERE ProductName LIKE ? " + 
		"GROUP BY Storage " + 
		"ORDER BY CASE " +
		"    WHEN Storage = '1TB' THEN 1 " +
		"    WHEN Storage = '512GB' THEN 2 " +
		"    WHEN Storage = '256GB' THEN 3 " +  
		"    WHEN Storage = '128GB' THEN 4 " +
		"    ELSE 5 " +
		"END";
		 try {
			return jdbcTemplate.query(sql, new Object[]{storage.trim() + "%"}, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					// Trả về dữ liệu storage (Dung lượng)
					return rs.getString("Storage");
				}
			});
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
	}
    public List<iPhone> getPhoneColor(String name, String storage) {
		String sql = "SELECT * FROM Products WHERE ProductName LIKE ? AND Storage LIKE ?";
		try {
			// Loại bỏ khoảng trắng thừa trước và sau tên và dung lượng
			name = name.trim();
			storage = storage.trim();
	
			// Debug thông tin để kiểm tra giá trị query
			System.out.println("SQL Query: " + sql);
			System.out.println("Name: " + name);
			System.out.println("Storage: " + storage);
	
			return jdbcTemplate.query(sql, new Object[]{name , storage + "%"}, new RowMapper<iPhone>() {
				@Override
				public iPhone mapRow(ResultSet rs, int rowNum) throws SQLException {
					// Trả về đối tượng iPhone từ kết quả truy vấn
					return new iPhone(
						rs.getInt("ProductID"),
						rs.getString("ProductName"),
						rs.getInt("CategoryID"),
						rs.getInt("Price"),
						rs.getInt("Quantity"),
						rs.getString("Storage"),
						rs.getString("Color"),
						rs.getString("Image"),
						rs.getInt("IsNew"),
						rs.getString("Description"),
						rs.getString("timer"),
						rs.getInt("discount")
					);
				}
			});
		} catch (Exception e) {
			// Xử lý lỗi và in ra thông báo lỗi
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	public iPhone getProductById(int ID){
		String sql = "SELECT * FROM Products where ProductID= ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{ID}, this::mapRowToProduct);
	}
	public iPhone getProductByName(String  name){
		String sql = "SELECT * FROM Products where  ProductName= ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{name.trim()}, this::mapRowToProduct);
	}
	// List nay tri tra ve 1 cot
	public List<Map<String, Object>> getList(String sql) {
		try {
			 // Sử dụng query để truy vấn và lưu kết quả vào List<Map<String, Object>>
			return jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
				@Override
				public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
					Map<String, Object> result = new HashMap<>();
					result.put("id", rs.getInt(1));
					result.put("name", rs.getString(2));
					return result;
				}
			});
		} catch (Exception e) {
       	 e.printStackTrace();  // Bạn có thể log lỗi nếu cần
        return new ArrayList<>();  // Trả về danh sách rỗng trong trường hợp có lỗi
    	}
	}
	
	private iPhone mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
		return new iPhone(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryID"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Storage"),
                        rs.getString("Color"),
                        rs.getString("Image"),
                        rs.getInt("IsNew"),
                        rs.getString("Description"),
                        rs.getString("timer"),
						rs.getInt("discount")
                    );
	}
}	
