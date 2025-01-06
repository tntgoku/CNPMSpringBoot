package backend.demo.Model;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class iPhone {
    private int productID;          // Mã sản phẩm
    private String productName;     // Tên sản phẩm
    private int categoryID;         // Mã danh mục sản phẩm
    private int price;              // Giá bán
    private int quantity;           // Số lượng tồn kho
    private String storage;         // Dung lượng (256GB, 512GB, 1TB)
    private String color;           // Màu sắc
    private String image;           // URL ảnh sản phẩm
    private int isNew;              // Trạng thái (1: Mới, 0: Cũ)
    private String description;     // Mô tả chi tiết
    private String timer;           // Thời gian tạo sản phẩm
    private int discount;

///Constructor
    public iPhone() {
    }

    public iPhone(int productID, String productName, int categoryID, int price, int quantity, 
    String storage, String color, String image, int isNew, String description, String timer,int Discount) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.storage = storage;
        this.color = color;
        this.image = image;
        this.isNew = isNew;
        this.description = description;
        this.timer = timer;
        this.discount=Discount;
        }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    //Constructor
    public iPhone(int productID, String productName, int categoryID, int price, int quantity, 
    String storage, String color, String image, int isNew, String description, String timer) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.storage = storage;
        this.color = color;
        this.image = image;
        this.isNew = isNew;
        this.description = description;
        this.timer = timer;
        }

    public int getProductID() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getPrice() {
        return this.price;
    }
    public String getPriceString() {
         NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);

         String formattedNumber = formatter.format(this.price);
        return formattedNumber;
    }
    public String getPriceDiscountString() {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        int pricediscount= this.price-(this.price*(getDiscount()/100));
        String formattedNumber = formatter.format(pricediscount);
       return formattedNumber;
   }
    public void setPrice(double price) {
        this.price = (int)price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStorage() {
        return this.storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIsNew() {
        return this.isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimer() {
        return this.timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public iPhone productID(int productID) {
        setProductID(productID);
        return this;
    }

    public iPhone productName(String productName) {
        setProductName(productName);
        return this;
    }

    public iPhone categoryID(int categoryID) {
        setCategoryID(categoryID);
        return this;
    }

    public iPhone price(double price) {
        setPrice(price);
        return this;
    }

    public iPhone quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public iPhone storage(String storage) {
        setStorage(storage);
        return this;
    }

    public iPhone image(String image) {
        setImage(image);
        return this;
    }

    public iPhone isNew(int isNew) {
        setIsNew(isNew);
        return this;
    }

    public iPhone description(String description) {
        setDescription(description);
        return this;
    }

    public iPhone timer(String timer) {
        setTimer(timer);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof iPhone)) {
            return false;
        }
        iPhone iPhone = (iPhone) o;
        return productID == iPhone.productID && Objects.equals(productName, iPhone.productName) && categoryID == iPhone.categoryID && price == iPhone.price && quantity == iPhone.quantity && Objects.equals(storage, iPhone.storage) && Objects.equals(image, iPhone.image) && isNew == iPhone.isNew && Objects.equals(description, iPhone.description) && Objects.equals(timer, iPhone.timer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, categoryID, price, quantity, storage, image, isNew, description, timer);
    }

    @Override
    public String toString() {
        return "{" +
            " productID='" + getProductID() + "'" +
            ", productName='" + getProductName() + "'" +
            ", categoryID='" + getCategoryID() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", storage='" + getStorage() + "'" +
            ", image='" + getImage() + "'" +
            ", isNew='" + getIsNew() + "'" +
            ", description='" + getDescription() + "'" +
            ", timer='" + getTimer() + "',Discount" +getDiscount()+
            "}\n";
    }
    
}
