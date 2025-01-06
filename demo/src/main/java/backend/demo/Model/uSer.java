package backend.demo.Model;
import java.util.Objects;

public class uSer {
     // Thuộc tính tương ứng với các cột trong bảng Users
     private int userID;           // Mã người dùng
     private String fullName;      // Họ và tên
     private String email;         // Địa chỉ email
     private String passwordHash;  // Mật khẩu đã được băm
     private String phone;         // Số điện thoại
     private String address;       // Địa chỉ
     private int roles;            // Vai trò người dùng (ví dụ: 1 - Admin, 2 - Khách hàng, v.v.)
     private String timer;         // Thời gian tạo người dùng

    public uSer() {
    }

    public uSer(int userID, String fullName, String email, String passwordHash, String phone, String address, int roles, String timer) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.address = address;
        this.roles = roles;
        this.timer = timer;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoles() {
        return this.roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    public String getTimer() {
        return this.timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public uSer userID(int userID) {
        setUserID(userID);
        return this;
    }

    public uSer fullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    public uSer email(String email) {
        setEmail(email);
        return this;
    }

    public uSer passwordHash(String passwordHash) {
        setPasswordHash(passwordHash);
        return this;
    }

    public uSer phone(String phone) {
        setPhone(phone);
        return this;
    }

    public uSer address(String address) {
        setAddress(address);
        return this;
    }

    public uSer roles(int roles) {
        setRoles(roles);
        return this;
    }

    public uSer timer(String timer) {
        setTimer(timer);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof uSer)) {
            return false;
        }
        uSer uSer = (uSer) o;
        return userID == uSer.userID && Objects.equals(fullName, uSer.fullName) && Objects.equals(email, uSer.email) && Objects.equals(passwordHash, uSer.passwordHash) && Objects.equals(phone, uSer.phone) && Objects.equals(address, uSer.address) && roles == uSer.roles && Objects.equals(timer, uSer.timer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, fullName, email, passwordHash, phone, address, roles, timer);
    }

    @Override
    public String toString() {
        return "{" +
            " userID='" + getUserID() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", email='" + getEmail() + "'" +
            ", passwordHash='" + getPasswordHash() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", roles='" + getRoles() + "'" +
            ", timer='" + getTimer() + "'" +
            "}";
    }
}