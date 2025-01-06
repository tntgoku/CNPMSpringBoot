package backend.demo.Model;

public class cartItem {
    private int IDP;
    private int Quantity;
    private String Color;
    private int Prices;
    public cartItem(){}
    public cartItem(int iDP, int quantity, String color, int prices) {
        IDP = iDP;
        Quantity = quantity;
        Color = color;
        Prices = prices;
    }
    public int getIDP() {
        return IDP;
    }
    public void setIDP(int iDP) {
        IDP = iDP;
    }
    public int getQuantity() {
        return Quantity;
    }
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
    public String getColor() {
        return Color;
    }
    public void setColor(String color) {
        Color = color;
    }
    public int getPrices() {
        return Prices;
    }
    public void setPrices(int prices) {
        Prices = prices;
    }
    @Override
    public String toString() {
        return "'cartItem' :[IDP=" + IDP + ", Quantity=" + Quantity + ", Color=" + Color + ", Prices=" + Prices + "]";
    }
    
}
