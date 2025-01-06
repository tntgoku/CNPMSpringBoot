package backend.demo.Model;
import java.util.Objects;


public class OrderInfo {

    String idString;
    String totalPrice;
    String paymentTime;
    String transactionId;
    String IDUser;

    public OrderInfo() {
    }
    public OrderInfo(String idString, String totalPrice, String paymentTime, String transactionId) {
        this.idString = idString;
        this.totalPrice = totalPrice;
        this.paymentTime = paymentTime;
        this.transactionId = transactionId;
    }
    public OrderInfo(String idString, String totalPrice, String paymentTime, String transactionId, String IDUser) {
        this.idString = idString;
        this.totalPrice = totalPrice;
        this.paymentTime = paymentTime;
        this.transactionId = transactionId;
        this.IDUser = IDUser;
    }

    public String getIdString() {
        return this.idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentTime() {
        return this.paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getIDUser() {
        return this.IDUser;
    }

    public void setIDUser(String IDUser) {
        this.IDUser = IDUser;
    }

   

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderInfo)) {
            return false;
        }
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(idString, orderInfo.idString) && Objects.equals(totalPrice, orderInfo.totalPrice) && Objects.equals(paymentTime, orderInfo.paymentTime) && Objects.equals(transactionId, orderInfo.transactionId) && Objects.equals(IDUser, orderInfo.IDUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idString, totalPrice, paymentTime, transactionId, IDUser);
    }

    @Override
    public String toString() {
        return "{" +
            " idString='" + getIdString() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", paymentTime='" + getPaymentTime() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", IDUser='" + getIDUser() + "'" +
            "}";
    }
    
}