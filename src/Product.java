//Base class for all products the store will sell
public abstract class Product implements Comparable<Product>{
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int amountInCart;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        amountInCart=0;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public int getAmountInCart() {
        return amountInCart;
    }

    public double getPrice() {
        return price;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits() {
        if (amountInCart > 0 ) {
            soldQuantity += amountInCart;
            return price * amountInCart;
        }
        return 0.0;
    }
    public void addToCart(){
        amountInCart++;
        stockQuantity--;
    }
    public void removeFromCart(){
        stockQuantity++;
        amountInCart--;
    }
    public double calculateMoney(){
        if (amountInCart > 0 ) {
            return price * amountInCart;
        }
        return 0.0;
    }

    public int compareTo(Product p) {
        return p.soldQuantity-this.getSoldQuantity();
    }
}