package backend.demo.Model;
import java.util.List;
import java.util.Objects;

public class orDers {
    private String orderID;           // Mã đơn hàng
    private int userID;               // ID người dùng (Khách hàng)
    private String nameUser;          // Tên khách hàng
    private String orderDate;           // Ngày đặt hàng
    private String addressU;          // Địa chỉ giao hàng
    private String phone;             // Số điện thoại
    private String email;             // Email khách hàng
    private int totalAmount;       // Tổng giá trị đơn hàng
    private int status;               // Trạng thái đơn hàng (0: chờ xử lý, 1: đã hoàn thành, ...)
    private int payment;              // Phương thức thanh toán (1: tiền mặt, 2: thẻ, ...)
    private List<OrderDetails> orderDetails; // Danh sách chi tiết đơn hàng
    private String statustext;
    private String paymenttext;

    public orDers() {
    }
    public orDers(String orderID, int userID, String nameUser, String orderDate, String addressU, String phone, String email, int totalAmount, int status, int payment){
        this.orderID = orderID;
        this.userID = userID;
        this.nameUser = nameUser;
        this.orderDate = orderDate;
        this.addressU = addressU;
        this.phone = phone;
        this.email = email;
        this.totalAmount = totalAmount;
        this.status = status;
        this.payment = payment;
    }
    public orDers(String orderID, int userID, String nameUser, String orderDate, String addressU, String phone, String email, int totalAmount, int status, int payment, List<OrderDetails> orderDetails) {
        this.orderID = orderID;
        this.userID = userID;
        this.nameUser = nameUser;
        this.orderDate = orderDate;
        this.addressU = addressU;
        this.phone = phone;
        this.email = email;
        this.totalAmount = totalAmount;
        this.status = status;
        this.payment = payment;
        this.orderDetails = orderDetails;
    }
    public void setStatusText(String s){
        this.statustext=s;
    }
    public void setpaymentText(String s){
        this.paymenttext=s;
    }
    public String getpaymentText() {
        return this.paymenttext;
    }
    public String getStatusText() {
        return this.statustext;
    }
    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNameUser() {
        return this.nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddressU() {
        return this.addressU;
    }

    public void setAddressU(String addressU) {
        this.addressU = addressU;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPayment() {
        return this.payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public List<OrderDetails> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public orDers orderID(String orderID) {
        setOrderID(orderID);
        return this;
    }

    public orDers userID(int userID) {
        setUserID(userID);
        return this;
    }

    public orDers nameUser(String nameUser) {
        setNameUser(nameUser);
        return this;
    }

    public orDers orderDate(String orderDate) {
        setOrderDate(orderDate);
        return this;
    }

    public orDers addressU(String addressU) {
        setAddressU(addressU);
        return this;
    }

    public orDers phone(String phone) {
        setPhone(phone);
        return this;
    }

    public orDers email(String email) {
        setEmail(email);
        return this;
    }

    public orDers totalAmount(int totalAmount) {
        setTotalAmount(totalAmount);
        return this;
    }

    public orDers status(int status) {
        setStatus(status);
        return this;
    }

    public orDers payment(int payment) {
        setPayment(payment);
        return this;
    }

    public orDers orderDetails(List<OrderDetails> orderDetails) {
        setOrderDetails(orderDetails);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof orDers)) {
            return false;
        }
        orDers orDers = (orDers) o;
        return Objects.equals(orderID, orDers.orderID) && userID == orDers.userID && Objects.equals(nameUser, orDers.nameUser) && Objects.equals(orderDate, orDers.orderDate) && Objects.equals(addressU, orDers.addressU) && Objects.equals(phone, orDers.phone) && Objects.equals(email, orDers.email) && totalAmount == orDers.totalAmount && status == orDers.status && payment == orDers.payment && Objects.equals(orderDetails, orDers.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, userID, nameUser, orderDate, addressU, phone, email, totalAmount, status, payment, orderDetails);
    }

    @Override
    public String toString() {
        return "{" +
            " orderID='" + getOrderID() + "'" +
            ", userID='" + getUserID() + "'" +
            ", nameUser='" + getNameUser() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", addressU='" + getAddressU() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            ", status='" + getStatus() + "'" +
            ", payment='" + getPayment() + "'" +
            ", orderDetails='" + getOrderDetails() + "'" +
            "}";
    }

}