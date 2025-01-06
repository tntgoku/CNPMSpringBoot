package backend.demo.Model;
import java.util.Objects;

public class OrderDetails {
    private int orderDetailID;  // Mã chi tiết đơn hàng
    private String orderID;     // Mã đơn hàng
    private int productID;      // Mã sản phẩm
    private int quantity;       // Số lượng sản phẩm
    private int unitPrice;   // Giá của sản phẩm tại thời điểm đặt hàng
    private String Color,Storage;
    private String image;
    private String name;
    public OrderDetails() {
    }
    public OrderDetails(int orderDetailID, String orderID, int productID,String color,String storage, int quantity, 
    int unitPrice) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        Color=color;
        Storage=storage;
    }

    public OrderDetails(int orderDetailID, String orderID, int productID,String color,String storage, int quantity, 
    int unitPrice,String imga,String namE) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        Color=color;
        Storage=storage;
        this.image=imga;
        this.name=namE;
    }

    public OrderDetails(int orderDetailID, String orderID, int productID, int quantity, int unitPrice) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public void setimg(String i) {
        this.image= i;
    }
    public String getimg() {
        return this.image;
    }

    public void setname(String i) {
        this.name= i;
    }
    public String getname() {
        return this.name;
    }

    public String getColor() {
        return Color;
    }
    public void setColor(String color) {
        Color = color;
    }
    public String getStorage() {
        return Storage;
    }
    public void setStorage(String storage) {
        Storage = storage;
    }
    public int getOrderDetailID() {
        return this.orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderDetails orderDetailID(int orderDetailID) {
        setOrderDetailID(orderDetailID);
        return this;
    }

    public OrderDetails orderID(String orderID) {
        setOrderID(orderID);
        return this;
    }

    public OrderDetails productID(int productID) {
        setProductID(productID);
        return this;
    }

    public OrderDetails quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public OrderDetails unitPrice(int unitPrice) {
        setUnitPrice(unitPrice);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderDetails)) {
            return false;
        }
        OrderDetails orderDetails = (OrderDetails) o;
        return orderDetailID == orderDetails.orderDetailID && Objects.equals(orderID, orderDetails.orderID) && productID == orderDetails.productID && quantity == orderDetails.quantity && unitPrice == orderDetails.unitPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailID, orderID, productID, quantity, unitPrice);
    }

    @Override
    public String toString() {
        return "{" +
            " orderDetailID='" + getOrderDetailID() + "'" +
            ", orderID='" + getOrderID() + "'" +
            ", productID='" + getProductID() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            "}";
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

}
