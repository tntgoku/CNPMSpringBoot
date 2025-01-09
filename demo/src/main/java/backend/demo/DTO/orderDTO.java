package backend.demo.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import backend.demo.Model.OrderDetails;
import backend.demo.Model.iPhone;
import backend.demo.Model.orDers;
@Component
public class orderDTO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public orderDTO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderDetails> findDetails(String ID){
		String sql = "select * from OrderDetails WHERE OrderID LIKE ?";
		try {
            return jdbcTemplate.query(sql, new Object[]{ID.trim()}, new RowMapper<OrderDetails>() {
				@Override
				public OrderDetails mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new OrderDetails(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getInt(7)
                    );
                }
			});
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
        }
	}

    public List<orDers> getllOrder(String ID){
        String sql="SELECT * FROM Orders WHERE ODERID LIKE ? ";
        // String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";
		try {
			return jdbcTemplate.query(sql,new Object[]{ID.trim()+"%"},
             new RowMapper<orDers>() {
				@Override
				public orDers mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new orDers(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
						rs.getInt(10)
                    );
				}
			});
			
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
    }
 
    public List<OrderDetails> findDetailsImg(String ID){
		String sql = "   select OrderDetails.*,Products.Image,Products.ProductName from OrderDetails  join Products on Products.ProductID = OrderDetails.ProductID WHERE OrderDetails.OrderID LIKE ?";
		try {
            return jdbcTemplate.query(sql, new Object[]{ID.trim()}, new RowMapper<OrderDetails>() {
				@Override
				public OrderDetails mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new OrderDetails(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getInt(7),
                                rs.getString(8),
                                rs.getString(9)
                    );
                }
			});
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
        }
	}
    public List<orDers> getllOrderSearchName(String ID){
        String sql="SELECT * FROM Orders WHERE Nameuser LIKE ? ";
        // String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";
		try {
			return jdbcTemplate.query(sql,new Object[]{ID.trim()+"%"},
             new RowMapper<orDers>() {
				@Override
				public orDers mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new orDers(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
						rs.getInt(10)
                    );
				}
			});
			
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
    }
    public orDers findOrDersbyID(String id){
        String sql="SELECT * FROM Orders  WHERE OrderID = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{id.trim()}, this::mapRow);

    }
    public List<orDers> getllOrder(){
        String sql="SELECT * FROM Orders ";
        // String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";
		try {
			return jdbcTemplate.query(sql,
             new RowMapper<orDers>() {
				@Override
				public orDers mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new orDers(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
						rs.getInt(10)
                    );
				}
			});
			
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
    }

        // Thêm đơn hàng mới vào bảng Orders
        public int addOrder(String orderID, int userID, String nameUser, String addressU, String phone, String email, 
                        double totalAmount, int status, int payment) {
        String sql = "INSERT INTO Orders (OrderID, UserID, Nameuser, OrderDate, AddressU, Phone, Email, TotalAmount, Status, Payment) " +
                     "VALUES (?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, orderID, userID, nameUser, addressU, phone, email, totalAmount, status, payment);
        }


        public int addOrder(orDers orDer){
             String sql = "INSERT INTO Orders (OrderID, UserID, Nameuser, OrderDate, AddressU, Phone, Email, TotalAmount, Status, Payment) " +
                     "VALUES (?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?)";
                     return jdbcTemplate.update(sql,
                                                orDer.getOrderID(),
                                                orDer.getUserID(),
                                                orDer.getNameUser(),
                                                orDer.getAddressU(),
                                                orDer.getPhone(),
                                                orDer.getEmail(),
                                                orDer.getTotalAmount(),
                                                orDer.getStatus(),
                                                orDer.getPayment());
        }
        // Cập nhật đơn hàng trong bảng Orders
        public int updateOrder(orDers order) {
            String sql = "UPDATE Orders SET " +
                        "UserID = ?, " +
                        "Nameuser = ?, " +
                        "OrderDate = GETDATE(), " +
                        "AddressU = ?, " +
                        "Phone = ?, " +
                        "Email = ?, " +
                        "TotalAmount = ?, " +
                        "Status = ?, " +
                        "Payment = ? " +
                        "WHERE OrderID = ?";
            return jdbcTemplate.update(sql, 
                                    order.getUserID(),
                                    order.getNameUser(),
                                    order.getAddressU(),
                                    order.getPhone(),
                                    order.getEmail(),
                                    order.getTotalAmount(),
                                    order.getStatus(),
                                    order.getPayment(),
                                    order.getOrderID());
        }

         // Thêm chi tiết đơn hàng vào bảng OrderDetails
        public int addOrderDetail(String orderID, int productID, String color, String storage, int quantity, double unitPrice) {
            String sql = "INSERT INTO OrderDetails (OrderID, ProductID, Color, Storage, Quantity, UnitPrice) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, orderID, productID, color, storage, quantity, unitPrice);
        }

            // Phương thức tạo OrderID tự động theo ngày và số thứ tự
        public String generateOrderID() {
            // Lấy ngày hiện tại theo định dạng ddMMyyyy
            String datePart = new SimpleDateFormat("ddMMyyyy").format(new Date());

            // Lấy số thứ tự đơn hàng trong ngày
            String sql = "SELECT COUNT(*) FROM Orders WHERE OrderID LIKE ?";
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{datePart + "%"}, Integer.class);

            // Tạo mã OrderID: "ORD-ddMMyyyy-0001"
            String orderID = "ORD" + datePart + "-" + String.format("%04d", count + 1);

            // Kiểm tra xem mã OrderID đã tồn tại chưa
            while (checkOrderIDExists(orderID)) {
                // Nếu trùng, tăng số thứ tự và kiểm tra lại
                count++;
                orderID = "ORD" + datePart + "-" + String.format("%04d", count + 1);
            }

            return orderID;
        }
        
        
        // Phương thức kiểm tra OrderID có trùng hay không
        public boolean checkOrderIDExists(String orderID) {
            String sql = "SELECT COUNT(*) FROM Orders WHERE OrderID = ?";
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{orderID}, Integer.class);
            return count > 0;  // Nếu có bản ghi trùng, trả về true
        }
        private orDers mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new orDers(
                rs.getString(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getInt(8),
                rs.getInt(9),
                rs.getInt(10)
            );
	    }


        private OrderDetails getdatDetailsResultSet(ResultSet rs, int rowNum) throws SQLException {
            return new OrderDetails(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getInt(7)
            );
	    }

        public List<Map<Integer, String>> getlistStatus() {
            List<Map<Integer, String>> listtemp = new ArrayList<>();
            
            // Thêm ví dụ dữ liệu vào danh sách
            Map<Integer, String> status0 = new HashMap<>();
            Map<Integer, String> status1 = new HashMap<>();
            Map<Integer, String> status2 = new HashMap<>();
            Map<Integer, String> status3 = new HashMap<>();
            status0.put(0, "Chờ xác nhận");
            status1.put(1, "Chờ lấy hàng");
            status2.put(2, "Chờ giao hàng");
            status3.put(3, "Giao hàng thành công");

            listtemp.add(status0);
            listtemp.add(status1);
            listtemp.add(status2);
            listtemp.add(status3);
            return listtemp;
        }

        public List<Map<Integer, String>> getlistPayment() {
            List<Map<Integer, String>> listtemp = new ArrayList<>();
            
            // Thêm ví dụ dữ liệu vào danh sách
            Map<Integer, String> status0 = new HashMap<>();
            Map<Integer, String> status1 = new HashMap<>();
            Map<Integer, String> status2 = new HashMap<>();
            Map<Integer, String> status3 = new HashMap<>();
            status0.put(0, "COD");
            status1.put(1, "VNPAY");
            status2.put(2, "MOMO");

            listtemp.add(status0);
            listtemp.add(status1);
            listtemp.add(status2);
            return listtemp;
        }


        	
	private orDers mapRowToOrDers(ResultSet rs, int rowNum) throws SQLException {
        return new orDers(
            rs.getString(1),
            rs.getInt(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rs.getInt(8),
            rs.getInt(9),
            rs.getInt(10)
        );
	}
}
