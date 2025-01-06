package backend.demo.Model;

public class cartList {
    private iPhone item;
    private int quantity;
    private int totalPrice;
    
    @Override
    public String toString() {
        return "cartList [item=" + item + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
    }
    public cartList(){

    }
    public cartList(iPhone item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
    public String prices(){
        return item.getPriceDiscountString();
    }
    public cartList(iPhone item, int quantity, int totalPrice) {
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
    public iPhone getItem() {
        return item;
    }
    public void setItem(iPhone item) {
        this.item = item;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    
}
